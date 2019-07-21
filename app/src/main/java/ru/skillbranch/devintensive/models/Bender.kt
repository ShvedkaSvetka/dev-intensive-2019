package ru.skillbranch.devintensive.models

class Bender (var status:Status=Status.NORMAL, var question:Question = Question.NAME, var mistakes: Int = 0){

    val MAX_MISTAKES = 3

    fun askQuestion():String = when (question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION-> Question.PROFESSION.question
        Question.MATERIAL-> Question.MATERIAL.question
        Question.BDAY-> Question.BDAY.question
        Question.SERIAL-> Question.SERIAL.question
        Question.IDLE-> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        val (isValid, validationText) = question.validate(answer)
        return when {
            question == Question.IDLE -> {
                mistakes = 0
                question.question to status.color
            }
            question.answers.contains(answer.toLowerCase()) -> {
                mistakes = 0
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            }
            mistakes++ < MAX_MISTAKES -> {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            }
            else -> {
                mistakes = 0
                status = Status.NORMAL
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }
        }
    }

    enum class Status(val color: Triple<Int,Int,Int>){
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0)) ;

        fun nextStatus():Status{
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }

    enum class Question (val question:String, val answers: List<String>){
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun validate(answer: String): Pair<Boolean, String?> {
                return (answer.isNotEmpty() && answer[0].isLetter() && answer[0].isUpperCase()) to
                        "Это неправильный ответ"
            }

            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun validate(answer: String): Pair<Boolean, String?> {
                return (answer.isNotEmpty() && answer[0].isLetter() && answer[0].isLowerCase()) to
                        "Это неправильный ответ"
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
            override fun validate(answer: String): Pair<Boolean, String?> {
                return (answer.isNotEmpty() && !answer.matches(Regex(".*\\d+.*"))) to
                        "Материал не должен содержать цифр"
            }

        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun validate(answer: String): Pair<Boolean, String?> {
                return (answer.isNotEmpty() && answer.matches(Regex("[0-9]+"))) to
                        "Год моего рождения должен содержать только цифры"
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun validate(answer: String): Pair<Boolean, String?> {
                return (answer.isNotEmpty() && answer.matches(Regex("[0-9]{7}"))) to
                        "Серийный номер содержит только цифры, и их 7"
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
            override fun validate(answer: String): Pair<Boolean, String?> {
                return true to ""
            }
        };

        abstract fun nextQuestion():Question
        abstract fun validate(answer: String): Pair<Boolean, String?>
    }
}
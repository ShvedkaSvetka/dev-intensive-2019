package ru.skillbranch.devintensive.utils

object Utils{
    fun parseFullName(fullName:String?): Pair<String, String> {
        val strFullName = fullName?.trim()?.ifEmpty { null }
        val parts :List<String>? = strFullName?.split(" ")

        val firstName = parts?.getOrNull(0)?:"null"
        val lastName = parts?.getOrNull(1)?:"null"

        return firstName to lastName
    }

    fun transliteration(payload:String, devider:String = " "):String{
        return ""
    }

    fun toInitials(firstName:String?, lastName:String?): String{
        return ""
    }

    fun plurarizeTime(value: Int, pluralForms:String):String{
        val forms = pluralForms.split(";")

        when(value % 10){
            1 -> if(value % 100 != 11)
               return  "$value ${forms[0]}"
            in 2..4 -> if (value % 100 !in 12..14){
                return "$value ${forms[1]}"
            }
        }
        return "$value ${forms[2]}"
    }
}
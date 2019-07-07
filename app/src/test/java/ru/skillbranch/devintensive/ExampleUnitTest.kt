package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val NAME = "John";
    val LASTNAME="Dohn"

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance(){
        val user = User("1")
        val user1 = User("2", "Mini", "Mall")
        val user2 = User("3", "Timka", "Cat")

        user.printMe()
        user1.printMe()
        user2.printMe()

        println("$user $user1 $user2")
    }

    @Test
    fun test_parseFullName(){
        val user = User.makeUser("$NAME $LASTNAME")

        assertEquals(Pair("$NAME", "$LASTNAME"), user.printMe())

        val user1 = User.makeUser(null)
        assertEquals(Pair("null", "null"), user1.printMe())

        val user2 = User.makeUser("$NAME")
        assertEquals(Pair("$NAME", "null"), user2.printMe())

        val user3 = User.makeUser("")
        assertEquals(Pair("null", "null"), user3.printMe())

        val user4 = User.makeUser(" ")
        assertEquals(Pair("null", "null"), user4.printMe())
    }

    @Test
    fun test_decomposition(){
        val user = User.makeUser("$NAME $LASTNAME")
        fun getUserInfo() = user
        val (id, firstName, lastName) = getUserInfo()
        println("${user.component1()} $firstName $lastName")


    }

    @Test
    fun test_dateFormat(){
        val formatter1 = SimpleDateFormat("HH:mm:ss dd.MM.yy")
        val formatter2 = SimpleDateFormat("HH:mm")
        val date = Date()

        assertEquals(formatter1.format(date), Date().format())
        assertEquals(formatter2.format(date), Date().format("HH:mm"))
    }

    @Test
    fun test_dateAdd(){
        val seconds = 2
        val days = -4*24

        val calendar1 = Calendar.getInstance()
        calendar1.time = Date()
        calendar1.add(Calendar.SECOND, seconds)


        val calendar2 = Calendar.getInstance()
        calendar2.time = Date()
        calendar2.add(Calendar.HOUR, days)


        assertEquals(calendar1.getTime().toString(), Date().add(seconds,TimeUnits.SECOND).toString())
        assertEquals(calendar2.getTime().toString(), Date().add(days, TimeUnits.HOUR).toString())
    }

    @Test
    fun test_dataMapping(){
        val user = User.makeUser("$NAME $LASTNAME")
        val userView = user.toUserView()

        userView.printMe()
    }

    @Test
    fun test_fumanizeDate(){
        val messageDate = Date()
        val currDate = Date()
        messageDate.time = currDate.time
        assertEquals("только что", messageDate.humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("только что", messageDate.add(-1, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("только что", messageDate.add(1, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("несколько секунд назад", messageDate.add(-2, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через несколько секунд", messageDate.add(2, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("несколько секунд назад", messageDate.add(-45, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через несколько секунд", messageDate.add(45, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("минуту назад", messageDate.add(-46, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через минуту", messageDate.add(46, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("минуту назад", messageDate.add(-75, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через минуту", messageDate.add(75, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("1 минуту назад", messageDate.add(-76, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 1 минуту", messageDate.add(76, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 2 минуты", messageDate.add(2, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("3 минуты назад", messageDate.add(-3, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("45 минут назад", messageDate.add(-45, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 45 минут", messageDate.add(45, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("час назад", messageDate.add(-46, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через час", messageDate.add(46, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("час назад", messageDate.add(-75, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через час", messageDate.add(75, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("1 час назад", messageDate.add(-76, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 1 час", messageDate.add(76, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("4 часа назад", messageDate.add(-4, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 4 часа", messageDate.add(4, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("6 часов назад", messageDate.add(-6, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 6 часов", messageDate.add(6, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("11 часов назад", messageDate.add(-11, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 11 часов", messageDate.add(11, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("22 часа назад", messageDate.add(-22, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 22 часа", messageDate.add(22, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("день назад", messageDate.add(-23, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через день", messageDate.add(23, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("день назад", messageDate.add(-26, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через день", messageDate.add(26, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("1 день назад", messageDate.add(-27, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 1 день", messageDate.add(27, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("12 дней назад", messageDate.add(-12, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 12 дней", messageDate.add(12, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("112 дней назад", messageDate.add(-112, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 112 дней", messageDate.add(112, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("360 дней назад", messageDate.add(-360, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 360 дней", messageDate.add(360, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более года назад", messageDate.add(-361, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более чем через год", messageDate.add(361, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более года назад", messageDate.add(-12345, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более чем через год", messageDate.add(12345, TimeUnits.DAY).humanizeDiff(currDate))
    }

    @Test
    fun makeMessageTest(){
        val user = User.makeUser("Иван Кузьмин")
        var message = BaseMessage.makeMessage(user, Chat("0"), Date(), "text", "any text message")

        assertTrue(message is TextMessage)
        assertEquals("0", message.id)
        assertTrue(message.from === user)
        message = BaseMessage.makeMessage(user, Chat("0"), Date(), "image", "https://anyurl.com",true)
        assertTrue(message is ImageMessage)
        assertEquals("1", message.id)
        assertTrue(message.isIncoming)
        assertEquals((message as ImageMessage).image, "https://anyurl.com")
    }


}

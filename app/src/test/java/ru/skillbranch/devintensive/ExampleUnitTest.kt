package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.models.User
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
}

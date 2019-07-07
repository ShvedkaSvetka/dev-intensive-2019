package ru.skillbranch.devintensive.extensions

import android.view.SoundEffectConstants
import ru.skillbranch.devintensive.utils.Utils
import ru.skillbranch.devintensive.utils.Utils.plurarizeTime
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.math.min

const val SECOND = 1000L
const val MINUTE = 60* SECOND
const val HOUR = 60* MINUTE
const val DAY = 24* HOUR

const val PLURAL_SECONDS="секунду;секунды;секунд"
const val PLURAL_MINUTES="минуту;минуты;минут"
const val PLURAL_HOURS="час;часа;часов"
const val PLURAL_DAYS="день;дня;дней"


fun Date.format(pattern: String="HH:mm:ss dd.MM.yy"):String{
    var dateFormat = SimpleDateFormat(pattern, Locale("ru"))

    return dateFormat.format(this)
}

fun Date.add(value:Int, units:TimeUnits): Date{

    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE ->value* MINUTE
        TimeUnits.HOUR -> value* HOUR
        TimeUnits.DAY -> value* DAY

        else -> throw IllegalStateException("invalid unit")
    }
this.time = time
    return this
}

fun Date.humanizeDiff(currentDate: Date = Date()):String{
    val difference =  this.time - currentDate.time

    return if(difference > 0) humanizeDiffFuture(abs(difference))
    else humanizeDiffPast(abs(difference))
}

private fun humanizeDiffFuture(difference:Long):String{
    return when(difference){
        in 0..(1* SECOND) -> "только что"
        in(1* SECOND)..(45*SECOND)->"через несколько секунд"
        in(45* SECOND)..(75*SECOND)->"через минуту"
        in (75* SECOND)..(45* MINUTE)-> {
            val minutes = TimeUnit.MILLISECONDS.toMinutes(difference)
            "через ${plurarizeTime(minutes.toInt(),PLURAL_MINUTES)}"
        }
        in (45 * MINUTE)..(75 * MINUTE)->"через час"
        in(75* MINUTE)..(22* HOUR)->{
            val hour = TimeUnit.MILLISECONDS.toHours(difference)
            "через ${plurarizeTime(hour.toInt(), PLURAL_HOURS)}"
        }
        in(22*HOUR)..(26*HOUR)->"через день"
        in(26*HOUR)..(360*DAY)->{
            val day = TimeUnit.MILLISECONDS.toDays(difference)
            "через ${plurarizeTime(day.toInt(), PLURAL_DAYS)}"
        }
        else -> "более чем через год"
    }
}

private fun humanizeDiffPast(difference:Long):String{
    return when(difference){
        in 0..(1* SECOND) -> "только что"
        in(1* SECOND)..(45*SECOND)->"несколько секунд назад"
        in(45 * SECOND)..(75 * SECOND)->"минуту назад"
        in (75 * SECOND)..(45 * MINUTE)-> {
            val minutes = TimeUnit.MILLISECONDS.toMinutes(difference)
            "${plurarizeTime(minutes.toInt(),PLURAL_MINUTES)} назад"
        }
        in(45 * MINUTE )..(75 * MINUTE)->"час назад"
        in(75* MINUTE)..(22* HOUR)->{
            val hour = TimeUnit.MILLISECONDS.toHours(difference)
            "${plurarizeTime(hour.toInt(), PLURAL_HOURS)} назад"
        }
        in(22*HOUR)..(26*HOUR)->"день назад"
        in(26*HOUR)..(360*DAY)->{
            val day = TimeUnit.MILLISECONDS.toDays(difference)
            "${plurarizeTime(day.toInt(), PLURAL_DAYS)} назад"
        }
        else -> "более года назад"
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(i: Int): String {
    val type = this
    return when(type){
        SECOND-> "${plurarizeTime(i,PLURAL_SECONDS)}"
        MINUTE->"${plurarizeTime(i, PLURAL_MINUTES)}"
        HOUR->"${plurarizeTime(i, PLURAL_HOURS)}"
        DAY->"${plurarizeTime(i, PLURAL_DAYS)}"
    }
    }
}
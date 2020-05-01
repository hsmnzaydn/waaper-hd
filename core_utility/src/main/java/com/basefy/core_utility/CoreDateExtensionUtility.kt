package com.basefy.core_utility

import com.basefy.core_utility.GenericExtensions.LOCALE_TR
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

const val DateStringPattern = "yyyy-MM-dd'T'HH:mm:ss"
const val DateStringPatternAlternate = "yyyy-MM-dd'T'HH:mm:ss"
const val UIDateStringPattern = "dd MMMM HH:mm"
const val UIDateStringPatternStartDate = "dd/MM/yyyy"
const val UIDateStringPatternStartHour = "HH:mm"
const val UIDateStringServerPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"


fun Date.isToday(): Boolean = isSameDay(Date())

fun Date.isTomorrow(): Boolean = isSameDay(Date().addDay(1))

fun Date.isSameDay(date: Date): Boolean = calendar().isSameDay(date)

fun Date.calendar(): Calendar = Calendar.getInstance().apply { time = this@calendar }

fun Calendar.isSameDay(date: Date): Boolean = isSameDay(date.calendar())

fun Calendar.isSameDay(cal: Calendar): Boolean =
    isSameYear(cal) && get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR)

fun Calendar.isSameYear(cal: Calendar): Boolean = get(Calendar.YEAR) == cal.get(Calendar.YEAR)

fun Date.addDay(days: Int): Date = calendar().addDay(days).time

fun Date.addYear(years: Int): Date = calendar().addYear(years).time

fun Calendar.addDay(days: Int): Calendar {
    add(Calendar.DATE, days)
    return this
}

fun Calendar.addYear(years: Int): Calendar {
    add(Calendar.YEAR, years)
    return this
}

fun Long.getMinutes(): Long {
    return TimeUnit.SECONDS.toMinutes(this)
}

fun Long.getSeconds(): Long {
    return this - (this.getMinutes() * 60)
}

fun Date.getMonthName(): String {
    return calendar().getDisplayName(Calendar.MONTH, Calendar.SHORT, LOCALE_TR)!!
}

fun Date.getDayOfMonth(): Int {
    return calendar().get(Calendar.DAY_OF_MONTH)
}

@JvmOverloads
fun Long?.toDateTime(datePattern: String = DateStringPattern): String {
    if (this == null) {
        return String.empty
    }

    val date = Date(this)
    val format = SimpleDateFormat(datePattern, LOCALE_TR)
    return format.format(date)
}

fun currentTime(): Long {
    return System.currentTimeMillis() / 1000
}

fun Long.toFormattedDate(): String {
    val format = SimpleDateFormat(UIDateStringPattern, LOCALE_TR)
    return format.format(Date(this))
}

fun String?.toFormattedDate(): String {
    if (this == null)
        return ""
    //this format createdDate we want
    val mSDF = SimpleDateFormat(UIDateStringPattern, LOCALE_TR)

    //this format createdDate actully present
    val formatter = SimpleDateFormat(DateStringPattern, LOCALE_TR)

    var result = ""
    try {
        //  result = mSDF.format(formatter.parse(this)!!)
        val date = formatter.parse(this)
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.HOUR_OF_DAY, 1)
        result = mSDF.format(cal.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return result
}

fun String?.toStartDate(type: Boolean): String {
    var mSDF: SimpleDateFormat? = null
    if (this == null)
        return ""
    //this format createdDate we want
    if (type) {
        mSDF = SimpleDateFormat(UIDateStringPatternStartDate, LOCALE_TR)

    } else {
        mSDF = SimpleDateFormat(UIDateStringPatternStartHour, LOCALE_TR)
    }

    //this format createdDate actully present
    val formatter = SimpleDateFormat(DateStringPatternAlternate, LOCALE_TR)

    var result = ""
    try {
        result = mSDF.format(formatter.parse(this)!!)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return result
}


@JvmOverloads
fun String?.toTimeStampLong(dateStringPattern: String = DateStringPattern): Long {
    var timeStamp: Long = -1
    if (this == null)
        return timeStamp + 1

    try {
        timeStamp = SimpleDateFormat(dateStringPattern, LOCALE_TR).parse(this)!!.time / 1000
    } catch (e: ParseException) {
        e.printStackTrace()
    } finally {
        return timeStamp + 1

    }
}


fun Int.secondToMinute(): Pair<String, String> {
    val hour = (this / 60).toString()
    val minute = (this % 60).toString()


    return Pair(hour, minute)
}

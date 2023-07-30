package id.haaweejee.moviedbandroid.ui.util

import java.text.SimpleDateFormat
import java.util.Locale

val dateOnly = SimpleDateFormat("yyyy-MM-dd", Locale.US)
val dateDayWithHalfMonthSecond = SimpleDateFormat("dd MMM yyyy", Locale.US)

fun String.localeDateDayParseHalfMonthSecond(): String {
    return try {
        val date = dateOnly.parse(this)
        dateDayWithHalfMonthSecond.format(date!!)
    } catch (ex: Exception) {
        ""
    }
}

fun String.formatDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("HH:mm:ss | dd MMMM yyyy", Locale.ENGLISH)

    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}
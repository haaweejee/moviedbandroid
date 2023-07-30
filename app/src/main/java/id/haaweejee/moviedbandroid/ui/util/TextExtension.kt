package id.haaweejee.moviedbandroid.ui.util

import kotlin.math.roundToInt

fun Double.simplifyNumber(): Double = (this * 10.0).roundToInt() / 10.0

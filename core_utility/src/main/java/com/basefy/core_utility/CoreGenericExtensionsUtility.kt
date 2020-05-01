@file:JvmName("Utility")

package com.basefy.core_utility

import com.basefy.core_utility.GenericExtensions.DECIMAL_FORMAT
import com.basefy.core_utility.GenericExtensions.DECIMAL_FORMAT_SYMBOLS
import com.basefy.core_utility.GenericExtensions.DECIMAL_FORMAT_SYMBOLS_TR
import com.basefy.core_utility.GenericExtensions.DECIMAL_FORMAT_TR
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

object GenericExtensions {
    val LOCALE_TR = Locale("tr", "TR")

    val DECIMAL_FORMAT = DecimalFormat.getInstance() as DecimalFormat
    val DECIMAL_FORMAT_SYMBOLS = DecimalFormatSymbols.getInstance()!!

    val DECIMAL_FORMAT_TR = NumberFormat.getInstance(LOCALE_TR) as DecimalFormat
    val DECIMAL_FORMAT_SYMBOLS_TR = DecimalFormatSymbols(LOCALE_TR)

    val TURKISH_CHARS = arrayOf("ş", "Ş", "ğ", "Ğ", "ı", "İ", "ü", "Ü", "ö", "Ö", "ç", "Ç")
    val NEUTRAL_CHARS = arrayOf(
        "DateStringPattern",
        "S",
        "g",
        "G",
        "i",
        "I",
        "u",
        "U",
        "o",
        "CommentsCallBack",
        "c",
        "C"
    )

}

@JvmOverloads
fun Double?.formatDecimal(decimalSeparator: Char = ',', groupingSeparator: Char = '.'): String {
    DECIMAL_FORMAT_TR.applyPattern("###.00")

    DECIMAL_FORMAT_SYMBOLS_TR.decimalSeparator = decimalSeparator
    DECIMAL_FORMAT_SYMBOLS_TR.groupingSeparator = groupingSeparator

    DECIMAL_FORMAT_TR.decimalFormatSymbols = DECIMAL_FORMAT_SYMBOLS_TR

    return DECIMAL_FORMAT_TR.format(this ?: 0.0)
}

fun Int?.formatDecimal(): String {
    DECIMAL_FORMAT_SYMBOLS.groupingSeparator = '.'
    DECIMAL_FORMAT.decimalFormatSymbols = DECIMAL_FORMAT_SYMBOLS
    return DECIMAL_FORMAT.format(this ?: 0)
}

fun Long?.formatDecimal(): String {
    DECIMAL_FORMAT_SYMBOLS.groupingSeparator = '.'
    DECIMAL_FORMAT.decimalFormatSymbols = DECIMAL_FORMAT_SYMBOLS
    return DECIMAL_FORMAT.format(this ?: 0L)
}

fun String.linefy(): String {
    if (this.isEmpty()) return this
    return this.replace(" ", "\n")
}

val String.Companion.empty: String
    get() = String()

val String.Companion.space: String
    get() = " "

fun String.Companion.join(vararg args: String?): String {
    val stringBuilder = StringBuilder()
    for (arg in args) {
        if (arg.isNullOrEmpty()) continue
        stringBuilder.append(arg)
    }
    return stringBuilder.toString()
}

fun String.Companion.join(separator: String, vararg args: String?): String {
    val stringBuilder = StringBuilder()
    for (arg in args) {
        if (arg.isNullOrEmpty()) continue
        if (stringBuilder.isNotEmpty()) stringBuilder.append(separator)
        stringBuilder.append(arg)
    }
    return stringBuilder.toString()
}

fun String?.safeGet(): String {
    return this ?: String.empty
}

fun String?.safeGet(default: String): String {
    return this ?: default
}

fun String?.isNotNullOrEmpty(): Boolean {
    return this?.let { this.isNotEmpty() } ?: false
}

fun Collection<Any>?.isNotNullOrEmpty(): Boolean {
    return this?.let { this.isNotEmpty() } ?: false
}

fun String?.safeContains(other: String, ignoreCase: Boolean = false): Boolean {
    if (this == null) return false
    return this.contains(other, ignoreCase)
}

fun String?.safeStartsWith(prefix: String, ignoreCase: Boolean = false): Boolean {
    if (this == null) return false
    return this.startsWith(prefix, ignoreCase)
}

fun Boolean?.safeGet(): Boolean {
    return this ?: false
}

fun Int.isZero(): Boolean {
    return this == 0
}

fun Double.isZero(): Boolean {
    return this.toInt() == 0
}

fun String.modifiedDeepLink(): String {
    var url = this.substring(this.lastIndexOf("/") + 1)
    if (url.contains('?')) {
        url = url.split("?")[0]
    } else if (url.contains('%')) {
        url = url.split('%')[0]
    }
    return url
}
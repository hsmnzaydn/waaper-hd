package com.basefy.core_utility

import android.app.Activity
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.text.DecimalFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Double virgulden sonrası için 2 adet 0 koyar ve TL olarak döner
 * */
fun Double.toPrice(): String {
    val price = this
    val decimalFormat = DecimalFormat("#.## TL")
    return decimalFormat.format(price).toString().replace(".", ",")
}

/**
 * URI'yi File'ye çevirmek için kullanılır
 * @param activity: Dosyanın çevireleceği activity
 * @param uri: Çevirilmek istenen URI
 */
fun URItoFile(activity: Activity, uri: Uri): File {
    var filePath: String
    val cursor = activity.getContentResolver().query(uri, null, null, null, null)
    if (cursor == null) {
        filePath = uri.getPath()!!
    } else {
        try {
            cursor!!.moveToFirst()
            val idx = cursor!!.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            filePath = cursor!!.getString(idx)
            cursor!!.close()
        } catch (e: RuntimeException) {
            filePath = PathUtils.getFilePathForN(activity, uri)
        }
    }
    return File(filePath)
}


/**
 * Girilen passwordte 1 adet büyük 1 adet küçük ve rakam içerip içermemesine bakar
 */
fun String.isPassword(): Boolean {
    val PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$"
    var pattern: Pattern = Pattern.compile(PASSWORD_PATTERN)
    lateinit var matcher: Matcher
    matcher = pattern.matcher(this)
    return matcher.matches()
}

/**
 *Girilen email pattern e uygun mu diye kontrol eden method
 */
fun String.isEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

/**
 *TC Kimlik Numarası 11 haneli olmalı
 *TC Kimlik Numarası 0 ile başlamaz.
 *TC Kimlik Numarasının son rakamı tek sayı olamaz
 *TC kimlik numarasının 1,3,5,7,9 (tek haneler) hanelerinin toplamından 7 katından 2,4,6,8 (çift haneler) haneleri toplamının çıkarırsak mod 10 a göre 10. rakamı vermesi  gerekir.
 *TC kimlik numarasının ilk 10 hanesinin toplamnının mod 10 a göre 11. rakamı vermesi  gerekir.
 * */
fun String.isTC(): Boolean {
    when {
        this.length != 11 -> false
        this.take(1) == "0" -> false
        this.takeLast(1).toInt() % 2 == 1 -> false
        numberSum(this) % 10 != this.takeLast(1).toInt() -> false
        else -> true
    }
    return true
}
fun numberSum(number: String): Int {
    var sum = 0
    number.forEach {
        sum += it.toInt()
    }
    return sum
}



package com.nicekkong.kotlinboot.utils

import java.net.URLDecoder
import java.net.URLEncoder


object StringUtils {

    fun urlEncode(origin:String): String = URLEncoder.encode(origin, "UTF-8")

    fun urlDecode(encStr: String): String = URLDecoder.decode(encStr, "UTF-8")
}
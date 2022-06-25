package com.nicekkong.kotlinboot.Utils

import humanize.Humanize
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*


object StringUtils {

    fun urlEncode(origin:String): String = URLEncoder.encode(origin, "UTF-8")

    fun urlDecode(encStr: String): String = URLDecoder.decode(encStr, "UTF-8")
}
package com.nicekkong.kotlinboot.exception.dto

data class CustomException(val code:Int, val message:String?, val ex:Exception)

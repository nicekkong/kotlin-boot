package com.nicekkong.kotlinboot.exception.dto

data class CustomException(val code:String, val message:String?, val ex:Exception)

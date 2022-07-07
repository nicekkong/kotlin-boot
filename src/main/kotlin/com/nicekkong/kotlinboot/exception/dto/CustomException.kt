package com.nicekkong.kotlinboot.exception.dto

data class CustomException(
    val code:String,
    val message:String?,
    val detailErrorMessage:String? = null,
    val ex:Exception
)

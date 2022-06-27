package com.nicekkong.kotlinboot.exception.enumType

import org.springframework.http.HttpStatus

enum class ExceptionInfo(val code:Int, val message:String = "Exception Occur~!!", val status: HttpStatus = HttpStatus.OK, ) {
}
package com.nicekkong.kotlinboot.exception.dto

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

// TODO: 분석 및 사용 여부 판단 필요
class CustomErrorResponse {
    var code:String? = null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    var timestamp: LocalDateTime? = null
//    var status:Int? = null
    var errorMessage: String? = null
    var stackTrace:String? = null


    override fun toString():String {
        return "[ErrorMessage]" +
                "\ncode: $code" +
                "\ncode: $code" +
                "\rtimestamp: $timestamp" +
                "\nerrorMessage: $errorMessage" +
                "\nstackTrace: $stackTrace"
    }
}

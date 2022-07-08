package com.nicekkong.kotlinboot.exception.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

// TODO: 분석 및 사용 여부 판단 필요
class UserExceptionResponse {
    var code:String? = null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    var timestamp: LocalDateTime? = null
//    var status:Int? = null
    var errorMessage: String? = null
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var detailErrorMessage: String? = null      // enum class를 통한 정적 message가 아닌 동적 message를 제공할 경우
    var stackTrace:String? = null


    override fun toString():String {
        return "[ErrorMessage]" +
                "\ncode: $code" +
                "\ncode: $code" +
                "\rtimestamp: $timestamp" +
                "\nerrorMessage: $errorMessage" +
                "\ndetailErrorMessage: $detailErrorMessage" +
                "\nstackTrace: $stackTrace"
    }
}

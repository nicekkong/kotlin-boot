package com.nicekkong.kotlinboot.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.nicekkong.kotlinboot.exception.enumType.ApiErrorCode
import com.nicekkong.kotlinboot.exception.enumType.ErrorCode

data class ApiResponse<T>(
    val code: String = ApiResponseCode.SUCCESS.code,
    val message: String? = ApiResponseCode.FAIL.message,
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    val body: T? = null,
) {
    companion object {
        fun error(message: String?): ApiResponse<Unit> =
            ApiResponse(message = message)

        fun error(code: ApiErrorCode, message: String?): ApiResponse<Unit> =
            ApiResponse(code = code.code, message = message)

        fun error(code: ErrorCode, message: String?): ApiResponse<Unit> =
            ApiResponse(code = code.name, message = message)

        fun <T> success(body: T?): ApiResponse<T> = ApiResponse(body = body)

        fun success() = ApiResponse(body = null)

        fun fail() = ApiResponse(code = ApiResponseCode.FAIL.code,
            message = ApiResponseCode.FAIL.message, body = null)
    }
}

enum class ApiResponseCode(
    val code:String, val message:String,
) {

    SUCCESS("0000", "Success"),
    FAIL("9999", "Fail"),
}
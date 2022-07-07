package com.nicekkong.kotlinboot.exception.aop

import com.nicekkong.kotlinboot.exception.UserMessageException
import com.nicekkong.kotlinboot.exception.dto.CustomErrorResponse
import com.nicekkong.kotlinboot.exception.dto.CustomException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime


@RestControllerAdvice(value = ["com.nicekkong.kotlinboot"])
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {  }

    @ExceptionHandler(value = [Exception::class, ])
    fun globalExceptionHandler(ex: Exception, request: WebRequest?,):
            ResponseEntity<CustomErrorResponse> {

        val exceptionInfo = CustomException(code = "50000", message = ex.message, ex = ex)
//        val exceptionInfo = UserMessageException(ex.message, ex)

        val errors = CustomErrorResponse().apply {
            code = exceptionInfo.code
            timestamp = LocalDateTime.now()
            errorMessage = exceptionInfo.message
            stackTrace = exceptionInfo.ex.stackTraceToString()
        }
        logger.error{"[RestControllerAdvice]Error~!! ==> $errors"}

         return ResponseEntity<CustomErrorResponse>(errors, HttpStatus.OK)
    }

    @ExceptionHandler(value = [UserMessageException::class, ])
    fun userMessageExceptionHandler(ex: UserMessageException, request: WebRequest?,):
            CustomErrorResponse {

        val exceptionInfo = CustomException(code = ex.code?:"50000", message = ex.message,
            detailErrorMessage = ex.detailErrorMessage, ex = ex)
//        val exceptionInfo = UserMessageException(ex.message, ex)

        val errors = CustomErrorResponse().apply {
            code = exceptionInfo.code
            timestamp = LocalDateTime.now()
            errorMessage = exceptionInfo.message
            detailErrorMessage = exceptionInfo.detailErrorMessage
            stackTrace = exceptionInfo.ex.stackTraceToString()
        }
        logger.error{"[RestControllerAdvice]Error~!! ==> $errors"}

        return errors
    }
}

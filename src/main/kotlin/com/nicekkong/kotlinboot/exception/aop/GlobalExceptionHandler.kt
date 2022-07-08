package com.nicekkong.kotlinboot.exception.aop

import com.nicekkong.kotlinboot.exception.UserMessageException
import com.nicekkong.kotlinboot.exception.dto.UserExceptionResponse
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
            ResponseEntity<UserExceptionResponse> {

        val errors = UserExceptionResponse().apply {
            code = "50000"
            timestamp = LocalDateTime.now()
            errorMessage = ex.message
            stackTrace = ex.stackTraceToString()
        }
        logger.error{"[RestControllerAdvice]Error~!! ==> $errors"}

         return ResponseEntity<UserExceptionResponse>(errors, HttpStatus.OK)
    }

    @ExceptionHandler(value = [UserMessageException::class, ])
    fun userMessageExceptionHandler(ex: UserMessageException, request: WebRequest?,):
            UserExceptionResponse {

//        val exceptionInfo = CustomException(code = ex.code?:"50000", message = ex.message,
//            detailErrorMessage = ex.detailErrorMessage, ex = ex)
//        val exceptionInfo = UserMessageException(ex.message, ex)

        val errors = UserExceptionResponse().apply {
            code = ex.code?:"50000"
            timestamp = LocalDateTime.now()
            errorMessage = ex.message
            detailErrorMessage = ex.detailErrorMessage
            stackTrace = ex.stackTraceToString()
        }
        logger.error{"[RestControllerAdvice]Error~!! ==> $errors"}

        return errors
    }
}

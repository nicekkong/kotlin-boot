package com.nicekkong.kotlinboot.exception.aop

import com.nicekkong.kotlinboot.exception.BadRequestException
import com.nicekkong.kotlinboot.exception.UserMessageException
import com.nicekkong.kotlinboot.exception.dto.CustomErrorResponse
import com.nicekkong.kotlinboot.exception.dto.CustomException
import jdk.jshell.spi.ExecutionControl
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

    @ExceptionHandler(value = [Exception::class, RuntimeException::class, BadRequestException::class])
    fun globalExceptionHandler(ex: Exception, request: WebRequest?,):
            ResponseEntity<CustomErrorResponse> {
        logger.error{"[RestControllerAdvice]Error~!! ${ex.stackTraceToString()}"}

        val exceptionInfo = CustomException(50001, ex.message, ex)
//        val exceptionInfo = UserMessageException(ex.message, ex)

        val errors = CustomErrorResponse().apply {
            code = exceptionInfo.code
            timestamp = LocalDateTime.now()
            errorMessage = exceptionInfo.message
            stackTrace = exceptionInfo.ex.stackTraceToString()
        }

         return ResponseEntity<CustomErrorResponse>(errors, HttpStatus.OK)
    }
}

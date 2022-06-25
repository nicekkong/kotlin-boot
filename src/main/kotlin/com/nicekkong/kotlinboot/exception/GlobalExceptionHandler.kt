package com.nicekkong.kotlinboot.exception

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime


// TODO: 분석 및 사용 여부 판단 필요
@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {  }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandler(ex: Exception, request: WebRequest?):
            ResponseEntity<CustomErrorResponse> {
        val errors = CustomErrorResponse()
        errors.timestamp = LocalDateTime.now()
        errors.error = ex.message
        errors.status = HttpStatus.NOT_FOUND.value()
        errors.exception = ex.stackTraceToString()

        logger.error{"Error~!! ${ex.stackTraceToString()}"}

        return ResponseEntity<CustomErrorResponse>(errors, HttpStatus.NOT_FOUND)
    }
}

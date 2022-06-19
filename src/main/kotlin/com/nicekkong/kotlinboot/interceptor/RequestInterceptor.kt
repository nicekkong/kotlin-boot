package com.nicekkong.kotlinboot.interceptor

import com.nicekkong.kotlinboot.config.logger
import mu.KLogging
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


private val logger = KotlinLogging.logger {}


@Component
class RequestInterceptor : HandlerInterceptor {

    private val log = logger()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info{"[klog]request.getRequestURL() => ${request.requestURL}"}
        log.info("[log]request.getRequestURL() => ${request.requestURL}")
        return super.preHandle(request, response, handler)
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        (response.status).takeIf{it != 200}?.let { status ->
            log.error("Response Status => $status , URL => ${request.requestURI}")
            val stream:ServletOutputStream = response.outputStream
            log.error("Error!! $stream")
        }
        super.postHandle(request, response, handler, modelAndView)
    }

}
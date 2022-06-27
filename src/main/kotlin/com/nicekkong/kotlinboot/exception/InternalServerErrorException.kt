package com.nicekkong.kotlinboot.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class InternalServerErrorException : RuntimeException {
    constructor(msg: String?) : super(msg) {}
    constructor(msg: String?, cause: Throwable?) : super(msg, cause) {}
}
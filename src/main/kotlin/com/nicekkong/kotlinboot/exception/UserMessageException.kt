package com.nicekkong.kotlinboot.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.OK)
class UserMessageException : RuntimeException {
    var code:String? = null
    constructor(code: String?, message: String?) : super(message) {this.code = code?:"50000"}
    constructor(code: String?, message: String?, cause: Throwable?) : super(message, cause) {}
}

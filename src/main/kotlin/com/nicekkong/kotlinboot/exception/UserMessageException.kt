package com.nicekkong.kotlinboot.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Service단에서 발생되는 예외를 공통적으로 처리하기 위한 Exception
 * 가급적 해당 Exception을 직접 사용하기 보다는 UserMessageException 을 상속받아 개별 Service에 대한 Exception을 만들어 사용한다.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
open class UserMessageException : RuntimeException {
    var code:String? = null
    var detailErrorMessage:String? = null

    constructor(code: String? = null, message: String? = null, detailErrorMessage:String? = null, cause: Throwable? = null):
            super(message, cause) {
                this.code = code?:"50000"
                this.detailErrorMessage = detailErrorMessage
            }
}

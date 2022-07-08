package com.nicekkong.kotlinboot.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Service 단에서 사용하는 서비스별 예외 정의 class
 * UserMessageException을 상속받도록 하여 AOP로 정의된 GlobalExceptionHandler를 통해 에러 메시지를 생성하도록 한다.
  */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class MyException : UserMessageException {
    constructor(code: String, message: String, cause: Throwable?):
            super(code = code, message = message, cause = cause) {}
    constructor(myErrorCode: MyErrorCode, cause: Throwable? = null):
            super(code = myErrorCode.code, message = myErrorCode.message, cause = cause) {}
}

/**
 * 해당 예외 class에서 정의하여 사용할 에러 code와 message를 정의한다.
 * 예외 발생시 가능하면 enum class에 정의되어 있는 값을 파라메터로 전달하도록 한다.
 */
enum class MyErrorCode (
    val code: String,
    val message: String,
) {
    MY_ERROR("50003", "my error")
}


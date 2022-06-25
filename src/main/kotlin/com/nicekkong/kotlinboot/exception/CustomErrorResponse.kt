package com.nicekkong.kotlinboot.exception

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

// TODO: 분석 및 사용 여부 판단 필요
class CustomErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    var timestamp: LocalDateTime? = null
    var status = 0
    var error: String? = null
    var exception:String? = null
}

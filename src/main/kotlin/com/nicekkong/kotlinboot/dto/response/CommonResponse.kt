package com.nicekkong.kotlinboot.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

class CommonResponse<T> (
    var code:String? = "0000",
    var message:String? = "success",
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var body:T? = null,
)
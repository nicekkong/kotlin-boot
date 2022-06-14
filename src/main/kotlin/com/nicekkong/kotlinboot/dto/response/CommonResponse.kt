package com.nicekkong.kotlinboot.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

class CommonResponse<T> (
    var code:String? = null,
    var message:String? = null,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var body:T? = null,
)
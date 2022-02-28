package com.nicekkong.kotlinboot.dto.response

class CommonResponse<T> {

    var code:String? = null
    var message:String? = null

    var body:T? = null

}
package com.nicekkong.kotlinboot.zample.dto.response

class EmployeeDto (
    var id:Long? = 0,
    var name:String? = null
) {

    fun getMyName():String {
        return "${name}님"
    }
}


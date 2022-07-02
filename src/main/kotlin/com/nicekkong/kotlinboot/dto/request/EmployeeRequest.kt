package com.nicekkong.kotlinboot.dto.request

import com.fasterxml.jackson.annotation.JsonValue


data class EmployeeRequest (
    val name:String,
    val job:String,
    val deptName:String,
) {
}

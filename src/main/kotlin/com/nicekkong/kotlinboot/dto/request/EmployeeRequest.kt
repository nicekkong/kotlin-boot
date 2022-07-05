package com.nicekkong.kotlinboot.dto.request

import com.fasterxml.jackson.annotation.JsonValue
import org.jetbrains.annotations.NotNull


data class EmployeeRequest (
    @NotNull
    val name:String,
    val job:String,
    val deptName:String,
)
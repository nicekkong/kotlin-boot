package com.nicekkong.kotlinboot.zample.dto.request

import org.jetbrains.annotations.NotNull


data class EmployeeRequest (
    @NotNull
    val name:String,
    val job:String,
    val deptName:String,
)
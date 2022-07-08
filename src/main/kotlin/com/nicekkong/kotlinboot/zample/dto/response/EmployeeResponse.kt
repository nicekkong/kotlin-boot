package com.nicekkong.kotlinboot.zample.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty


data class EmployeeResponse(
    val id:Long,
    val name:String,
    @JsonProperty(value = "work_job")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val job:String? = null,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val deptName:String? = null,
)

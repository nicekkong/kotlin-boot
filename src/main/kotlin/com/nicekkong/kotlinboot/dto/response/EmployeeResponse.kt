package com.nicekkong.kotlinboot.dto.response

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude

data class EmployeeResponse(
    val id:Long,
    val name:String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val job:String? = null,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val deptName:String? = null,
)

package com.nicekkong.kotlinboot.zample.dto.response

class TeamDto (
    val id:Long,
    val name:String
) {

    fun getMyName():String {
        return "${name}님"
    }
}


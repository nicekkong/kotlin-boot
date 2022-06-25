package com.nicekkong.kotlinboot.dto.response

import com.nicekkong.kotlinboot.annotation.NoArgs

class TeamDto (
    val id:Long,
    val name:String
) {

    fun getMyName():String {
        return "${name}님"
    }
}


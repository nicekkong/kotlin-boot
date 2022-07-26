package com.nicekkong.kotlinboot.zample.dto.response

import java.util.*

data class RandomUserResponse(
    val results: List<Results>,
    val info:Info,
) {
    // Nested class(static classs)
    data class Results(
        val gender:String,
        val name:Name,
        val nat:String,
    ) {
        data class Name(
            val title:String,
            val first:String,
            val last:String,
        )
    }

    data class Info(
        val seed:String,
        val results:Int,
        val page:Int,
        val version:String,
    )
}






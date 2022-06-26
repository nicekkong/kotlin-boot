package com.nicekkong.kotlinboot.component

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class MyComponent {

    @Bean
    fun myName():String {
        return "nicekkong"
    }

    @Bean
    fun myInfo(myName:String) : String{
        return "${myName}님"
    }
}
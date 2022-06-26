package com.nicekkong.kotlinboot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MyConfiguration {

    @Bean
    fun myCName():String {
        return "nicekkong"
    }

    @Bean
    fun myCInfo(myName:String) : String{
        return "${myName}님"
    }

}
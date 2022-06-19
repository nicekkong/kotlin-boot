package com.nicekkong.kotlinboot.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory

//https://jungseob86.tistory.com/17
inline fun <reified T> T.logger(): Logger {
    if (T::class.isCompanion) {
        return LoggerFactory.getLogger(T::class.java.enclosingClass)
    }
    return LoggerFactory.getLogger(T::class.java)
}
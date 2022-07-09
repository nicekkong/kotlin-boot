package com.nicekkong.kotlinboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class KotlinBootApplication

fun main(args: Array<String>) {
    runApplication<KotlinBootApplication>(*args)
}

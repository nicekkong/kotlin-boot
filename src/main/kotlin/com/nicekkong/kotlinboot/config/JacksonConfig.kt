package com.nicekkong.kotlinboot.config

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    // https://thxwelchs.github.io/Kotlin%20JPA%20Entity%20%EB%AA%A8%EB%8D%B8%EB%A7%81%ED%95%98%EA%B8%B0/
    // Hibernate5Module이 지연로딩 되는 객체의 프로퍼티 값이 비어있더라도 직렬화를 가능하게 해준다.
    // 이 모듈을 bean으로 등록 안하면 hibernateLazyInitializer 라는 하이버네이트 프록시 객체의 프로퍼티를 직렬화 하려다가 에러남
    @Bean
    fun hibernate5Module():Hibernate5Module = Hibernate5Module()

}
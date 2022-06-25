package com.nicekkong.kotlinboot.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration(proxyBeanMethods = false)
class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource")
    fun dataSource():DataSource = DataSourceBuilder.create().build()

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun dataSource(properties: DataSourceProperties): HikariDataSource? {
        return properties.initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
    }

}
package com.example.backendkotlin

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import java.sql.Connection

@Configuration
class DataSourceConfig(
    private val jdbc: JdbcTemplate,
) {

    @Bean
    fun getDataSourceConnection(): Connection? {
        return jdbc.dataSource?.connection
    }
}
package com.example.backendkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class BackendKotlinApplication

fun main(args: Array<String>) {
    runApplication<BackendKotlinApplication>(*args)
}

package com.example.backendkotlin.presentation

import com.example.backendkotlin.presentation.response.HelloResponse
import com.example.backendkotlin.usecase.HelloUsecase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val usecase: HelloUsecase,
) {
    @GetMapping("/hello")
    fun sayHello(): HelloResponse {
        return usecase.getHelloResponse()
    }
}

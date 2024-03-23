package com.example.backendkotlin.presentation

import com.example.backendkotlin.presentation.response.HelloResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun sayHello(): HelloResponse {
        return HelloResponse("Hello Werewolf!!", emptyList())
    }
}
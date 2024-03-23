package com.example.backendkotlin.presentation

import com.example.backendkotlin.presentation.response.HelloResponse
import com.example.backendkotlin.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val repository: UserRepository,
) {
    @GetMapping("/hello")
    fun sayHello(): HelloResponse {
        val user = repository.selectUser(12345)
        return HelloResponse("Hello Werewolf!!", user)
    }
}
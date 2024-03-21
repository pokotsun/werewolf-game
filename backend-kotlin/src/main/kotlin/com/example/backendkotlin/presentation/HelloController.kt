package com.example.backendkotlin.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun sayHello(): Response {
        return Response("Hello Werewolf!!")
    }

    data class Response(val msg: String)
}
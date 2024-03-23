package com.example.backendkotlin.usecase

import com.example.backendkotlin.presentation.response.HelloResponse
import com.example.backendkotlin.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class HelloUsecase(private val repository: UserRepository) {
    fun getHelloResponse(): HelloResponse {
        val user = repository.selectUser(12345)
        return HelloResponse("Hello Werewolf!!", user)
    }
}

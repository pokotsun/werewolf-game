package com.example.backendkotlin.presentation.response

import java.util.*

data class HelloResponse(val value: String, val users: User?) {
    data class User(val userId: UUID, val name: String)
}

package com.example.backendkotlin.presentation.response

data class HelloResponse(val value: String, val users: User?) {
    data class User(val userId: Int, val name: String)
}
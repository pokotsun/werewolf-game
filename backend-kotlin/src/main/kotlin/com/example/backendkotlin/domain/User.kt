package com.example.backendkotlin.domain

data class User(
    val id: UserId,
    val name: String,
    val isActive: Boolean,
)

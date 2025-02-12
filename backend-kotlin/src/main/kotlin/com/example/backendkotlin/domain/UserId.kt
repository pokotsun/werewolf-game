package com.example.backendkotlin.domain

import java.util.UUID

data class UserId(val value: UUID) {
    companion object {
        fun generate(): UserId {
            return UserId(UUID.randomUUID())
        }
    }
}

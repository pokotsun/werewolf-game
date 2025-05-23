package com.example.backendkotlin.domain

enum class WerewolfErrorCode(val code: String) {
    VILLAGE_PASSWORD_IS_WRONG("VILLAGE_PASSWORD_IS_WRONG"),
    USER_PASSWORD_IS_WRONG("USER_PASSWORD_IS_WRONG"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND"),
    LACK_VILLAGE_USER("LACK_VILLAGE_USER"),
}

package com.example.backendkotlin.infrastructure.db.table

import org.jetbrains.exposed.dao.id.UUIDTable

object UserTable : UUIDTable("user") {
    val name = varchar("name", 255)
    val isActive = bool("is_active")
}
package com.example.backendkotlin.repository

import com.example.backendkotlin.infrastructure.db.table.UserTable
import com.example.backendkotlin.presentation.response.HelloResponse
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository() {

    fun selectUser(id: Int): HelloResponse.User? {
        val queryResult = transaction {
            UserTable.select(
                UserTable.id,
                UserTable.name,
            ).firstOrNull()
        }
        return queryResult?.let { HelloResponse.User(it[UserTable.id].value, it[UserTable.name]) }
    }
}

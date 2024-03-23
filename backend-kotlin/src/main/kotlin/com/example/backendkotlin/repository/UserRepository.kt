package com.example.backendkotlin.repository

import com.example.backendkotlin.presentation.response.HelloResponse
import com.example.generated.Queries
import com.example.generated.QueriesImpl
import org.springframework.stereotype.Repository
import java.sql.Connection

@Repository
class UserRepository(
    private val connection: Connection,
) {
    private val queryManager: Queries = QueriesImpl(connection)

    fun selectUser(id: Int): HelloResponse.User? {
        val userDao = queryManager.getUser(id)
        return userDao?.let { HelloResponse.User(it.userId, it.name) }
    }
}

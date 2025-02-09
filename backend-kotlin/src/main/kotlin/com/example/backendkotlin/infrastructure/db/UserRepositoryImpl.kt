package com.example.backendkotlin.infrastructure.db

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserRepository
import com.example.backendkotlin.infrastructure.db.table.UserTable
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

/**
 * UserRepositoryの実装
 */
@Repository
class UserRepositoryImpl() : UserRepository {
    /**
     * {@inheritDoc}
     */
    override fun createUser(user: User): User {
        try {
            transaction {
                UserTable.insert {
                    it[id] = user.id.value
                    it[name] = user.name
                    it[isActive] = user.isActive
                }
            }
        } catch (e: ExposedSQLException) {
            throw RuntimeException("Failed to create user", e)
        }
        return user
    }
}

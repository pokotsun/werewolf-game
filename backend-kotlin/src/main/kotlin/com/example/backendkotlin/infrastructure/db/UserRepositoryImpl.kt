package com.example.backendkotlin.infrastructure.db

import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserRepository
import com.example.backendkotlin.infrastructure.db.table.UserTable
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
    override fun createUser(user: User, hashedPassword: HashedPassword): User {
        transaction {
            UserTable.insert {
                it[id] = user.id.value
                it[name] = user.name
                it[passwordHash] = hashedPassword.value
                it[isActive] = user.isActive
            }
        }
        return user
    }
}

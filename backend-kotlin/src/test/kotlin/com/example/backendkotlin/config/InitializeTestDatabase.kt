package com.example.backendkotlin.config

import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable
import com.example.backendkotlin.infrastructure.db.table.UserTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import io.kotest.core.listeners.BeforeProjectListener
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.testcontainers.containers.PostgreSQLContainer

object InitializeTestDatabase : BeforeProjectListener {
    private const val POSTGRESQL_IMAGE = "postgres:14.11"
    private val testContainer = PostgreSQLContainer<Nothing>(POSTGRESQL_IMAGE).apply {
        withDatabaseName("werewolf")
        withUsername("werewolf")
        withPassword("password")
        withExposedPorts(5432)
    }

    override suspend fun beforeProject() {
        testContainer.start()
        Database.connect(
            url = testContainer.jdbcUrl,
            driver = "org.postgresql.Driver",
            user = testContainer.username,
            password = testContainer.password,
        )
        transaction {
            SchemaUtils.create(
                VillageTable,
                UserTable,
                RUserVillageTable,
            )
        }
    }
}

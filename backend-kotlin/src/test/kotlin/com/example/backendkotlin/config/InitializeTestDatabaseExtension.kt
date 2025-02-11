package com.example.backendkotlin.config

import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable
import com.example.backendkotlin.infrastructure.db.table.UserTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import io.kotest.core.listeners.BeforeProjectListener
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.testcontainers.containers.PostgreSQLContainer

/**
 * DBの初期設定をまとめたクラス
 */
object InitializeTestDatabaseExtension : BeforeProjectListener {
    private const val POSTGRESQL_IMAGE = "postgres:14.11"
    private val testContainer = PostgreSQLContainer<Nothing>(POSTGRESQL_IMAGE).apply {
        withDatabaseName("werewolf")
        withUsername("werewolf")
        withPassword("password")
    }

    override suspend fun beforeProject() {
        testContainer.start()
        val db = Database.connect(
            url = testContainer.jdbcUrl,
            driver = testContainer.driverClassName,
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
        // 初期設定が終わったのでコネクションを閉じる
        TransactionManager.closeAndUnregister(db)

        // Springの世界に入ります
        System.setProperty("spring.datasource.url", testContainer.jdbcUrl)
        System.setProperty("spring.datasource.username", testContainer.username)
        System.setProperty("spring.datasource.password", testContainer.password)
    }
}

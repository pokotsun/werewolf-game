package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.infrastructure.db.table.ActionLogTable
import com.example.backendkotlin.infrastructure.db.table.BitingResultLogTable
import com.example.backendkotlin.infrastructure.db.table.GameStatusTable
import com.example.backendkotlin.infrastructure.db.table.GameTable
import com.example.backendkotlin.infrastructure.db.table.PlayerTable
import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable
import com.example.backendkotlin.infrastructure.db.table.UserTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import com.example.backendkotlin.infrastructure.db.table.VotingResultLogTable
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * PostgreSQLのコンテナを使用するDescribeSpec共通の設定ファイル
 */
abstract class DescribeSpecUsingPostgreSQLTestContainer() : DescribeSpec() {
    // 全てのテスト後にUserTable, VillageTable, RUserVillageTableのデータを初期化する
    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        transaction {
            ActionLogTable.deleteAll()
            BitingResultLogTable.deleteAll()
            VotingResultLogTable.deleteAll()
            PlayerTable.deleteAll()
            GameStatusTable.deleteAll()
            GameTable.deleteAll()
            UserTable.deleteAll()
            VillageTable.deleteAll()
            RUserVillageTable.deleteAll()
        }
    }
}

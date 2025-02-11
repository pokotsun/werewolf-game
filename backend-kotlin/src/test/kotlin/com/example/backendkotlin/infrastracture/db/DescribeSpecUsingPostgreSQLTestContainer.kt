package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable
import com.example.backendkotlin.infrastructure.db.table.UserTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * PostgreSQLのコンテナを使用するDescribeSpec共通の設定ファイル
 */
abstract class DescribeSpecUsingPostgreSQLTestContainer() : DescribeSpec() {
    override suspend fun afterSpec(spec: Spec) {
        super.afterSpec(spec)

        // テスト終了後にテーブルを削除
        transaction {
            RUserVillageTable.deleteAll()
            VillageTable.deleteAll()
            UserTable.deleteAll()
        }
    }
}

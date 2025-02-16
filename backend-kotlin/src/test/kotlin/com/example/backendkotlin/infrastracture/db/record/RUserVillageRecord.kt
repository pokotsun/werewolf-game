package com.example.backendkotlin.infrastracture.db.record

import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * RUserVillageテーブルのレコードを表すクラス
 *
 * @property userId ユーザID
 * @property villageId 村ID
 */
data class RUserVillageRecord(
    val userId: UserId,
    val villageId: VillageId,
) {
    /**
     * RUserVillageTableにレコードを追加する
     *
     * @return RUserVillageRecord
     * */
    fun insert(): RUserVillageRecord {
        transaction {
            RUserVillageTable.insert {
                it[userId] = this@RUserVillageRecord.userId.value
                it[villageId] = this@RUserVillageRecord.villageId.value
            }
        }
        return this
    }
}

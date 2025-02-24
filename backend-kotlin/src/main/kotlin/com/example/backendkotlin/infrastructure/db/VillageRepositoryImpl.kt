package com.example.backendkotlin.infrastructure.db

import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

/**
 * VillageRepositoryの実装
 */
@Repository
class VillageRepositoryImpl() : VillageRepository {

    /**
     * {@inheritDoc}
     */
    override fun selectAllVillages(): List<Village> {
        // villageテーブルから全ての村を取得する
        val villageRecords = transaction {
            VillageTable.select(
                VillageTable.id,
                VillageTable.name,
                VillageTable.citizenCount,
                VillageTable.werewolfCount,
                VillageTable.fortuneTellerCount,
                VillageTable.knightCount,
                VillageTable.psychicCount,
                VillageTable.madmanCount,
                VillageTable.isInitialActionActive,
                VillageTable.gameMasterUserId,
            )
                .where { VillageTable.isRecruited eq true }
                .toList()
        }
        // village_idごとにcurrent_userを取得
        val villageIds = villageRecords.map { it[VillageTable.id].value }
        val userIdsPerVillageRecords = transaction {
            RUserVillageTable.select(
                RUserVillageTable.villageId,
                RUserVillageTable.userId,
            ).where {
                RUserVillageTable.villageId inList villageIds
            }.toList()
        }
        val userIdsPerVillageMap = userIdsPerVillageRecords
            .groupBy { it[RUserVillageTable.villageId] }
            .mapValues { values -> values.value.map { value -> value[RUserVillageTable.userId] } }
        return villageRecords.map { r ->
            val currentUserNumber = userIdsPerVillageMap[r[VillageTable.id].value]?.size
                ?: throw NoSuchElementException("RUserVillageへの登録漏れを検知しました。villageId: ${r[VillageTable.id]}")
            Village(
                id = VillageId(r[VillageTable.id].value),
                name = r[VillageTable.name],
                citizenCount = r[VillageTable.citizenCount],
                werewolfCount = r[VillageTable.werewolfCount],
                fortuneTellerCount = r[VillageTable.fortuneTellerCount],
                knightCount = r[VillageTable.knightCount],
                psychicCount = r[VillageTable.psychicCount],
                madmanCount = r[VillageTable.madmanCount],
                isInitialActionActive = r[VillageTable.isInitialActionActive],
                gameMasterUserId = UserId(r[VillageTable.gameMasterUserId]),
                currentUserNumber = currentUserNumber,
            )
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun createVillage(village: Village, passwordHash: String, salt: String): Village {
        transaction {
            VillageTable.insert {
                it[id] = village.id.value
                it[name] = village.name
                it[VillageTable.salt] = salt
                it[VillageTable.passwordHash] = passwordHash
                it[citizenCount] = village.citizenCount
                it[werewolfCount] = village.werewolfCount
                it[fortuneTellerCount] = village.fortuneTellerCount
                it[knightCount] = village.knightCount
                it[psychicCount] = village.psychicCount
                it[madmanCount] = village.madmanCount
                it[isInitialActionActive] = village.isInitialActionActive
                it[gameMasterUserId] = village.gameMasterUserId.value
            }
        }
        return village
    }
}

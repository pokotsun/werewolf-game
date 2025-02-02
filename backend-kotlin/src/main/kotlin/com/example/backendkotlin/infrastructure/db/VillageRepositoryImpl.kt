package com.example.backendkotlin.infrastructure.db

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import org.jetbrains.exposed.exceptions.ExposedSQLException
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
        val queryResult = transaction {
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
            ).toList()
        }
        return queryResult.map {
            Village(
                id = VillageId(it[VillageTable.id].value),
                name = it[VillageTable.name],
                citizenCount = it[VillageTable.citizenCount],
                werewolfCount = it[VillageTable.werewolfCount],
                fortuneTellerCount = it[VillageTable.fortuneTellerCount],
                knightCount = it[VillageTable.knightCount],
                psychicCount = it[VillageTable.psychicCount],
                madmanCount = it[VillageTable.madmanCount],
                isInitialActionActive = it[VillageTable.isInitialActionActive],
            )
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun createVillage(village: Village, passwordHash: String, salt: String): Village {
        try {
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
                }
            }
        } catch (e: ExposedSQLException) {
            throw RuntimeException("Failed to create village", e)
        }
        return village
    }
}

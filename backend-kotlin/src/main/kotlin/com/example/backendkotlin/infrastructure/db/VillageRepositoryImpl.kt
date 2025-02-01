package com.example.backendkotlin.infrastructure.db

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class VillageRepositoryImpl(): VillageRepository {

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
        return queryResult.map { Village(
            id = VillageId(it[VillageTable.id].value),
            name = it[VillageTable.name],
            citizenCount = it[VillageTable.citizenCount],
            werewolfCount = it[VillageTable.werewolfCount],
            fortuneTellerCount = it[VillageTable.fortuneTellerCount],
            knightCount = it[VillageTable.knightCount],
            psychicCount = it[VillageTable.psychicCount],
            madmanCount = it[VillageTable.madmanCount],
            isInitialActionActive = it[VillageTable.isInitialActionActive],
        ) }
    }
}

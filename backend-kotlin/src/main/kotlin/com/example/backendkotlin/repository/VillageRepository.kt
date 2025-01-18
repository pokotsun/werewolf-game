package com.example.backendkotlin.repository

import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable
import com.example.backendkotlin.infrastructure.db.table.UserTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import com.example.backendkotlin.presentation.response.VillageResponse
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class VillageRepository() {

    fun selectAllVillages(): List<VillageResponse> {
        val queryResult = transaction {
            VillageTable.join(
                RUserVillageTable,
                JoinType.LEFT,
                additionalConstraint = { VillageTable.id eq RUserVillageTable.villageId },
            ).join(
                UserTable,
                JoinType.LEFT,
                additionalConstraint = { RUserVillageTable.userId eq UserTable.id },
            ).select(
                VillageTable.id,
                VillageTable.name,
                VillageTable.updatedAt,
                UserTable.id,
            ).toList()
        }
        return queryResult.groupBy { VillageTable.id }.map { (_, values) ->
            val userCount = values.count()
            val firstValue = values.first()
            VillageResponse(
                firstValue[VillageTable.name],
                userCount,
                firstValue[VillageTable.updatedAt].toString(),
            )
        }
    }
}

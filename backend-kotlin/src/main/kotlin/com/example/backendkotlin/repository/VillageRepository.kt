package com.example.backendkotlin.repository

import com.example.backendkotlin.presentation.response.VillageResponse
import com.example.generated.Queries
import com.example.generated.QueriesImpl
import org.springframework.stereotype.Repository
import java.sql.Connection

@Repository
class VillageRepository(private val connection: Connection) {
    private val queryManager: Queries = QueriesImpl(connection)

    fun selectAllVillages(): List<VillageResponse> {
        return queryManager.getVillages().map {
            VillageResponse(it.name, it.userCounts.toInt(), it.updatedAt.toString())
        }
    }
}

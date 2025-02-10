package com.example.backendkotlin.infrastructure.db

import com.example.backendkotlin.domain.RUserVillageRepository
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

/**
 * RUserVillageRepositoryの実装クラス
 */
@Repository
class RUserVillageRepositoryImpl() : RUserVillageRepository {
    /**
     * {@inheritDoc}
     */
    override fun save(userId: UserId, villageId: VillageId): Pair<UserId, VillageId> {
        transaction {
            RUserVillageTable.insert {
                it[RUserVillageTable.userId] = userId.value
                it[RUserVillageTable.villageId] = villageId.value
            }
        }
        return Pair(userId, villageId)
    }
}

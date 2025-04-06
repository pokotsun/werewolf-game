package com.example.backendkotlin.infrastructure.db

import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserCredential
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageCredentialWithUserCredentials
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable
import com.example.backendkotlin.infrastructure.db.table.UserTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.ResultRow
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
    override fun selectAllVillages(isRecruitedOnly: Boolean): List<Village> {
        // villageテーブルから全ての村を取得する
        val selectStatement = VillageTable.select(
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
            VillageTable.isRecruited,
        )
        val villageRecords = transaction {
            if (isRecruitedOnly) {
                selectStatement
                    .where { VillageTable.isRecruited eq true }
                    .toList()
            } else {
                selectStatement.toList()
            }
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
                isRecruited = r[VillageTable.isRecruited],
            )
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun selectVillageById(villageId: VillageId): Pair<Village, HashedPassword>? {
        // villageテーブルから指定された村を取得する
        val villageRecord = transaction {
            VillageTable.select(
                VillageTable.id,
                VillageTable.name,
                VillageTable.passwordHash,
                VillageTable.citizenCount,
                VillageTable.werewolfCount,
                VillageTable.fortuneTellerCount,
                VillageTable.knightCount,
                VillageTable.psychicCount,
                VillageTable.madmanCount,
                VillageTable.isInitialActionActive,
                VillageTable.gameMasterUserId,
                VillageTable.isRecruited,
            )
                .where { VillageTable.id eq villageId.value }
                .firstOrNull()
        } ?: return null

        // village_idを指定してcurrent_userを取得
        val rUserVillageRecords = transaction {
            RUserVillageTable.select(
                RUserVillageTable.villageId,
                RUserVillageTable.userId,
            ).where {
                RUserVillageTable.villageId eq villageId.value
            }.toList()
        }
        val currentUserNumber = rUserVillageRecords.size

        // ドメインに変換する
        val village = mapToVillage(villageRecord, currentUserNumber)
        return Pair(
            village,
            HashedPassword(villageRecord[VillageTable.passwordHash]),
        )
    }

    /**
     * {@inheritDoc}
     */
    override fun selectVillageWithCurrentUsersById(villageId: VillageId): VillageCredentialWithUserCredentials? {
        // villageテーブルから指定された村を取得する
        val villageWithCurrentUsersRecords = transaction {
            VillageTable
                .join(
                    RUserVillageTable,
                    JoinType.LEFT,
                    VillageTable.id,
                    RUserVillageTable.villageId,
                ).join(
                    UserTable,
                    JoinType.LEFT,
                    RUserVillageTable.userId,
                    UserTable.id,
                ).select(
                    VillageTable.id,
                    VillageTable.name,
                    VillageTable.passwordHash,
                    VillageTable.citizenCount,
                    VillageTable.werewolfCount,
                    VillageTable.fortuneTellerCount,
                    VillageTable.knightCount,
                    VillageTable.psychicCount,
                    VillageTable.madmanCount,
                    VillageTable.isInitialActionActive,
                    VillageTable.gameMasterUserId,
                    VillageTable.isRecruited,
                    UserTable.id,
                    UserTable.name,
                    UserTable.passwordHash,
                    UserTable.isActive,
                ).where { VillageTable.id eq villageId.value }
                .toList()
        }

        // 指定された村が存在しない場合はnullを返す
        if (villageWithCurrentUsersRecords.isEmpty()) {
            return null
        }

        // ドメインに変換する
        val villageRecord = villageWithCurrentUsersRecords.first()
        val currentUserNumber = villageWithCurrentUsersRecords.size
        val village = mapToVillage(villageRecord, currentUserNumber)
        val villageHashedPassword = HashedPassword(villageRecord[VillageTable.passwordHash])
        val userCredentialList = villageWithCurrentUsersRecords.map {
            UserCredential(
                mapToUser(it),
                HashedPassword(it[UserTable.passwordHash]),
            )
        }
        return VillageCredentialWithUserCredentials(village, villageHashedPassword, userCredentialList)
    }

    /**
     * {@inheritDoc}
     */
    override fun createVillage(village: Village, hashedPassword: HashedPassword): Village {
        transaction {
            VillageTable.insert {
                it[id] = village.id.value
                it[name] = village.name
                it[passwordHash] = hashedPassword.value
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

    /**
     * VillageTableの検索結果をVillageドメインに変換する
     *
     * @param villageRecord VillageTableの検索結果
     * @param currentUserNumber 現在のユーザー数
     *
     * @return Villageドメイン
     */
    private fun mapToVillage(villageRecord: ResultRow, currentUserNumber: Int): Village {
        return Village(
            id = VillageId(villageRecord[VillageTable.id].value),
            name = villageRecord[VillageTable.name],
            citizenCount = villageRecord[VillageTable.citizenCount],
            werewolfCount = villageRecord[VillageTable.werewolfCount],
            fortuneTellerCount = villageRecord[VillageTable.fortuneTellerCount],
            knightCount = villageRecord[VillageTable.knightCount],
            psychicCount = villageRecord[VillageTable.psychicCount],
            madmanCount = villageRecord[VillageTable.madmanCount],
            isInitialActionActive = villageRecord[VillageTable.isInitialActionActive],
            gameMasterUserId = UserId(villageRecord[VillageTable.gameMasterUserId]),
            currentUserNumber = currentUserNumber,
            isRecruited = villageRecord[VillageTable.isRecruited],
        )
    }

    /**
     * UserTableの検索結果をUserドメインに変換する
     *
     * @param userRecord UserTableの検索結果
     *
     * @return Userドメイン
     */
    private fun mapToUser(userRecord: ResultRow): User {
        return User(
            id = UserId(userRecord[UserTable.id].value),
            name = userRecord[UserTable.name],
            isActive = userRecord[UserTable.isActive],
        )
    }
}

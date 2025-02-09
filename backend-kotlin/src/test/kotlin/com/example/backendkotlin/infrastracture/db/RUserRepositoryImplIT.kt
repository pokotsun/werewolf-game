package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastructure.db.RUserVillageRepositoryImpl
import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable
import com.example.backendkotlin.infrastructure.db.table.UserTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.Spec
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * RUserRepositoryImplの結合テスト
 *
 * TestContainerを利用してDBの接続を行い、RUserRepositoryImplのテストを行う
 */
class RUserRepositoryImplIT() : DescribeSpecUsingPostgreSQLTestContainer() {
    private companion object {
        const val EXIST_USER_ID_STRING = "00000000-0000-0000-0000-000000000000"
        const val EXIST_VILLAGE_ID_STRING = "99999999-9999-9999-9999-999999999999"
    }
    private val rUserVillageRepository = RUserVillageRepositoryImpl()

    // テスト前にUserTableとVillageTableに初期データを挿入する
    override suspend fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        val user = User(
            id = UserId(UUID.fromString(EXIST_USER_ID_STRING)),
            name = "ユーザ1",
            isActive = true,
        )
        val village = Village(
            id = VillageId(UUID.fromString(EXIST_VILLAGE_ID_STRING)),
            name = "村1",
            citizenCount = 10,
            werewolfCount = 2,
            fortuneTellerCount = 1,
            knightCount = 1,
            psychicCount = 1,
            madmanCount = 1,
            isInitialActionActive = false,
            gameMasterUserId = user.id,
        )
        val saltInput = "salt"
        val hashedPassword = "hashedPassword"
        transaction {
            UserTable.insert {
                it[id] = user.id.value
                it[name] = user.name
                it[isActive] = user.isActive
            }
            VillageTable.insert {
                it[id] = village.id.value
                it[name] = village.name
                it[salt] = saltInput
                it[passwordHash] = hashedPassword
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
    }

    init {
        // 全てのテスト後にRUserVillageTableのデータを初期化する
        afterTest {
            transaction {
                RUserVillageTable.deleteAll()
            }
        }

        describe("save") {
            context("正常系") {
                it("ユーザが村に参加する") {
                    // given
                    val userId = UserId(UUID.fromString(EXIST_USER_ID_STRING))
                    val villageId = VillageId(UUID.fromString(EXIST_VILLAGE_ID_STRING))

                    // when
                    val result = rUserVillageRepository.save(userId, villageId)

                    // then
                    result shouldBe Pair(userId, villageId)
                }
            }
            context("異常系") {
                it("同じユーザが同じ村に参加すると例外が発生する") {
                    // given
                    val userId = UserId(UUID.fromString(EXIST_USER_ID_STRING))
                    val villageId = VillageId(UUID.fromString(EXIST_VILLAGE_ID_STRING))

                    // and
                    shouldNotThrowAny { rUserVillageRepository.save(userId, villageId) }

                    // when
                    val exception = shouldThrow<RuntimeException> {
                        rUserVillageRepository.save(userId, villageId)
                    }

                    // then
                    exception.message shouldBe "Failed to create r_user_village"
                }
                it("UserTableに存在しないユーザが村に参加すると例外が発生する") {
                    // given
                    val userId = UserId(UUID.randomUUID())
                    val villageId = VillageId(UUID.fromString(EXIST_VILLAGE_ID_STRING))

                    // when, then
                    val exception = shouldThrow<RuntimeException> {
                        rUserVillageRepository.save(userId, villageId)
                    }

                    // then
                    exception.message shouldBe "Failed to create r_user_village"
                }
                it("VillageTableに存在しない村にユーザが参加すると例外が発生する") {
                    // given
                    val userId = UserId(UUID.fromString(EXIST_USER_ID_STRING))
                    val villageId = VillageId(UUID.randomUUID())

                    // when, then
                    val exception = shouldThrow<RuntimeException> {
                        rUserVillageRepository.save(userId, villageId)
                    }

                    // then
                    exception.message shouldBe "Failed to create r_user_village"
                }
            }
        }
    }
}

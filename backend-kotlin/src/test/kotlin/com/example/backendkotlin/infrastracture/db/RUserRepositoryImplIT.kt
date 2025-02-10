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
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

/**
 * RUserRepositoryImplの結合テスト
 *
 * TestContainerを利用してDBの接続を行い、RUserRepositoryImplのテストを行う
 */
@SpringBootTest
class RUserRepositoryImplIT(
    private val rUserVillageRepository: RUserVillageRepositoryImpl,
) : DescribeSpecUsingPostgreSQLTestContainer() {
    // 全てのテスト後にUserTable, VillageTable, RUserVillageTableのデータを初期化する
    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        super.afterTest(testCase, result)
        transaction {
            UserTable.deleteAll()
            VillageTable.deleteAll()
            RUserVillageTable.deleteAll()
        }
    }

    init {
        this.describe("save") {
            context("正常系") {
                it("ユーザが村に参加する") {
                    // given
                    val userId = UserId(UUID.randomUUID())
                    val user = User(userId, "user", true)
                    val villageId = VillageId(UUID.randomUUID())
                    val village = Village(
                        id = villageId,
                        name = "village",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = false,
                        gameMasterUserId = userId,
                    )
                    val saltInput = "salt"
                    val hashedPassword = "hashedPassword"

                    // and
                    transaction {
                        UserTable.insert {
                            it[id] = userId.value
                            it[name] = user.name
                            it[isActive] = user.isActive
                        }
                        VillageTable.insert {
                            it[id] = villageId.value
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

                    // when
                    val result = rUserVillageRepository.save(userId, villageId)

                    // then
                    result shouldBe Pair(userId, villageId)
                }
            }
            context("異常系") {
                it("同じユーザが同じ村に参加すると例外が発生する") {
                    // given
                    val userId = UserId(UUID.randomUUID())
                    val user = User(userId, "user", true)
                    val villageId = VillageId(UUID.randomUUID())
                    val village = Village(
                        id = villageId,
                        name = "village",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = false,
                        gameMasterUserId = userId,
                    )
                    val saltInput = "salt"
                    val hashedPassword = "hashedPassword"

                    // and
                    transaction {
                        UserTable.insert {
                            it[id] = userId.value
                            it[name] = user.name
                            it[isActive] = user.isActive
                        }
                        VillageTable.insert {
                            it[id] = villageId.value
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

                    // and
                    shouldNotThrowAny { rUserVillageRepository.save(userId, villageId) }

                    // when, then
                    shouldThrowExactly<ExposedSQLException> {
                        rUserVillageRepository.save(userId, villageId)
                    }
                }
                it("UserTableに存在しないユーザが村に参加すると例外が発生する") {
                    // given
                    val userId = UserId(UUID.randomUUID())
                    val user = User(userId, "user", true)
                    val villageId = VillageId(UUID.randomUUID())
                    val village = Village(
                        id = villageId,
                        name = "village",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = false,
                        gameMasterUserId = userId,
                    )
                    val saltInput = "salt"
                    val hashedPassword = "hashedPassword"
                    val anotherUserId = UserId(UUID.randomUUID())

                    // and
                    transaction {
                        UserTable.insert {
                            it[id] = userId.value
                            it[name] = user.name
                            it[isActive] = user.isActive
                        }
                        VillageTable.insert {
                            it[id] = villageId.value
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

                    // when, then
                    shouldThrowExactly<ExposedSQLException> {
                        rUserVillageRepository.save(anotherUserId, villageId)
                    }
                }
                it("VillageTableに存在しない村にユーザが参加すると例外が発生する") {
                    // given
                    val userId = UserId(UUID.randomUUID())
                    val user = User(userId, "user", true)
                    val villageId = VillageId(UUID.randomUUID())

                    // and
                    transaction {
                        UserTable.insert {
                            it[id] = userId.value
                            it[name] = user.name
                            it[isActive] = user.isActive
                        }
                    }

                    // when, then
                    shouldThrowExactly<ExposedSQLException> {
                        rUserVillageRepository.save(userId, villageId)
                    }
                }
            }
        }
    }
}

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
import java.util.UUID
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
        transaction {
            UserTable.deleteAll()
            VillageTable.deleteAll()
            RUserVillageTable.deleteAll()
        }
    }

    init {
        this.describe("selectByVillageId") {
            context("正常系") {
                it("存在してないVillageIdを指定しても空のリストが取得できる") {
                    // given
                    val villageId = VillageId.generate()

                    // when
                    val result = rUserVillageRepository.selectByVillageId(villageId)

                    // then
                    result shouldBe emptyList()
                }
                it("村に参加しているユーザーID一覧(1つ)を取得できる") {
                    // given
                    val userId = UserId.generate()
                    val user = User(userId, "user", true)
                    val villageId = VillageId.generate()
                    val village = Village(
                        id = villageId,
                        name = "村1",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = false,
                        gameMasterUserId = userId,
                    )

                    // and
                    transaction {
                        UserTable.insert {
                            it[UserTable.id] = userId.value
                            it[name] = user.name
                            it[isActive] = user.isActive
                        }
                        VillageTable.insert {
                            it[VillageTable.id] = villageId.value
                            it[name] = village.name
                            it[salt] = "salt"
                            it[passwordHash] = "hashedPassword"
                            it[citizenCount] = village.citizenCount
                            it[werewolfCount] = village.werewolfCount
                            it[fortuneTellerCount] = village.fortuneTellerCount
                            it[knightCount] = village.knightCount
                            it[psychicCount] = village.psychicCount
                            it[madmanCount] = village.madmanCount
                            it[isInitialActionActive] = village.isInitialActionActive
                            it[gameMasterUserId] = village.gameMasterUserId.value
                        }
                        RUserVillageTable.insert {
                            it[RUserVillageTable.userId] = userId.value
                            it[RUserVillageTable.villageId] = villageId.value
                        }
                    }

                    // when
                    val result = rUserVillageRepository.selectByVillageId(villageId)

                    // then
                    result shouldBe listOf(userId)
                }
                it("村に参加しているユーザーID一覧(複数)を取得できる") {
                    // given
                    val villageId = VillageId.generate()
                    val userId1 = UserId(UUID.randomUUID())
                    val userId2 = UserId(UUID.randomUUID())
                    val village = Village(
                        id = villageId,
                        name = "村1",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = false,
                        gameMasterUserId = userId1,
                    )

                    // and
                    transaction {
                        UserTable.insert {
                            it[UserTable.id] = userId1.value
                            it[name] = "ユーザ1"
                            it[isActive] = true
                        }
                        UserTable.insert {
                            it[UserTable.id] = userId2.value
                            it[name] = "ユーザ2"
                            it[isActive] = true
                        }
                        VillageTable.insert {
                            it[VillageTable.id] = villageId.value
                            it[name] = village.name
                            it[salt] = "salt"
                            it[passwordHash] = "hashedPassword"
                            it[citizenCount] = village.citizenCount
                            it[werewolfCount] = village.werewolfCount
                            it[fortuneTellerCount] = village.fortuneTellerCount
                            it[knightCount] = village.knightCount
                            it[psychicCount] = village.psychicCount
                            it[madmanCount] = village.madmanCount
                            it[isInitialActionActive] = village.isInitialActionActive
                            it[gameMasterUserId] = village.gameMasterUserId.value
                        }
                        RUserVillageTable.insert {
                            it[userId] = userId1.value
                            it[RUserVillageTable.villageId] = villageId.value
                        }
                        RUserVillageTable.insert {
                            it[userId] = userId2.value
                            it[RUserVillageTable.villageId] = villageId.value
                        }
                    }

                    // when
                    val result = rUserVillageRepository.selectByVillageId(villageId)

                    // then
                    result shouldBe listOf(userId1, userId2)
                }
            }
        }
        this.describe("save") {
            context("正常系") {
                it("ユーザが村に参加する") {
                    // given
                    val userId = UserId.generate()
                    val user = User(userId, "user", true)
                    val villageId = VillageId.generate()
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
                    val userId = UserId.generate()
                    val user = User(userId, "user", true)
                    val villageId = VillageId.generate()
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
                    val userId = UserId.generate()
                    val user = User(userId, "user", true)
                    val villageId = VillageId.generate()
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
                    val anotherUserId = UserId.generate()

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
                    val userId = UserId.generate()
                    val user = User(userId, "user", true)
                    val villageId = VillageId.generate()

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

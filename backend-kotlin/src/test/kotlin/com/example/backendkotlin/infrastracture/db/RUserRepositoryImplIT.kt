package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastracture.db.record.UserRecord
import com.example.backendkotlin.infrastracture.db.record.VillageRecord
import com.example.backendkotlin.infrastructure.db.RUserVillageRepositoryImpl
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.springframework.boot.test.context.SpringBootTest

/**
 * RUserRepositoryImplの結合テスト
 *
 * TestContainerを利用してDBの接続を行い、RUserRepositoryImplのテストを行う
 */
@SpringBootTest
class RUserRepositoryImplIT(
    private val rUserVillageRepository: RUserVillageRepositoryImpl,
) : DescribeSpecUsingPostgreSQLTestContainer() {
    init {
        this.describe("save") {
            context("正常系") {
                it("ユーザが村に参加する") {
                    // given
                    val userId = UserId.generate()
                    val villageId = VillageId.generate()
                    val user = User(userId, "user", true)
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
                    val expected = Pair(userId, villageId)
                    val saltInput = "salt"
                    val hashedPassword = "hashedPassword"

                    // and
                    UserRecord(user).insert()
                    VillageRecord(village, saltInput, hashedPassword).insert()

                    // when
                    val actual = rUserVillageRepository.save(userId, villageId)

                    // then
                    actual shouldBe expected
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
                    UserRecord(user).insert()
                    VillageRecord(village, saltInput, hashedPassword).insert()

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
                    UserRecord(user).insert()
                    VillageRecord(village, saltInput, hashedPassword).insert()

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
                    UserRecord(user).insert()

                    // when, then
                    shouldThrowExactly<ExposedSQLException> {
                        rUserVillageRepository.save(userId, villageId)
                    }
                }
            }
        }
    }
}

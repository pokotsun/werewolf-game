package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastracture.db.record.UserRecord
import com.example.backendkotlin.infrastracture.db.record.VillageRecord
import com.example.backendkotlin.infrastructure.db.RUserVillageRepositoryImpl
import com.example.backendkotlin.util.KSelect
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.instancio.Instancio
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
                    val userId = Instancio.create(UserId::class.java)
                    val user = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), userId)
                        .set(KSelect.field(User::isActive), true)
                        .create()

                    val villageId = Instancio.create(VillageId::class.java)
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .set(KSelect.field(Village::citizenCount), 10)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 1)
                        .set(KSelect.field(Village::isInitialActionActive), false)
                        .set(KSelect.field(Village::gameMasterUserId), userId)
                        .create()

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
                    val userId = Instancio.create(UserId::class.java)
                    val user = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), userId)
                        .set(KSelect.field(User::isActive), true)
                        .create()

                    val villageId = Instancio.create(VillageId::class.java)
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .set(KSelect.field(Village::citizenCount), 10)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 1)
                        .set(KSelect.field(Village::isInitialActionActive), false)
                        .set(KSelect.field(Village::gameMasterUserId), userId)
                        .create()

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
                    val userId = Instancio.create(UserId::class.java)
                    val user = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), userId)
                        .set(KSelect.field(User::isActive), true)
                        .create()

                    val villageId = Instancio.create(VillageId::class.java)
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .set(KSelect.field(Village::citizenCount), 10)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 1)
                        .set(KSelect.field(Village::isInitialActionActive), false)
                        .set(KSelect.field(Village::gameMasterUserId), userId)
                        .create()

                    val saltInput = "salt"
                    val hashedPassword = "hashedPassword"
                    val anotherUserId = Instancio.create(UserId::class.java)

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
                    val userId = Instancio.create(UserId::class.java)
                    val user = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), userId)
                        .set(KSelect.field(User::isActive), true)
                        .create()

                    val villageId = Instancio.create(VillageId::class.java)

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

package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastructure.db.VillageRepositoryImpl
import com.example.backendkotlin.infrastructure.db.table.UserTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.Spec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.assertj.core.error.ShouldContainExactlyInAnyOrder.shouldContainExactlyInAnyOrder
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
class VillageRepositoryImplIT(
    private val villageRepository: VillageRepositoryImpl,
) : DescribeSpecUsingPostgreSQLTestContainer() {
    // テスト前にGameMaster用のユーザーデータをUserTableに挿入する
    override suspend fun beforeSpec(spec: Spec) {
        transaction {
            val gameMaster = User(
                id = UserId(UUID.fromString(GAME_MASTER_USER_ID_STRING)),
                name = "gameMaster",
                isActive = true,
            )
            UserTable.insert {
                it[id] = gameMaster.id.value
                it[name] = gameMaster.name
                it[isActive] = gameMaster.isActive
            }
        }
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        // 毎テスト終了後に全レコードを削除
        transaction {
            VillageTable.deleteAll()
        }
    }

    init {
        this.describe("SelectAllVillages") {
            context("正常系") {
                it("村が1つもない場合、空のリストが返却される") {
                    // when
                    val actual = villageRepository.selectAllVillages()

                    // then
                    actual.shouldBeEmpty()
                }
                it("村が1つ返却される") {
                    // given
                    val expected = listOf(
                        Village(
                            id = VillageId.generate(),
                            name = "村1",
                            citizenCount = 10,
                            werewolfCount = 2,
                            fortuneTellerCount = 1,
                            knightCount = 1,
                            psychicCount = 1,
                            madmanCount = 1,
                            isInitialActionActive = true,
                            gameMasterUserId = UserId(UUID.fromString(GAME_MASTER_USER_ID_STRING)),
                        ),
                    )
                    val saltInput = "salt"
                    val hashedPassword = "passwordHash"

                    // and
                    transaction {
                        expected.forEach { village ->
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
                                it[gameMasterUserId] = UUID.fromString(GAME_MASTER_USER_ID_STRING)
                            }
                        }
                    }

                    // when
                    val actual = villageRepository.selectAllVillages()

                    // then
                    actual.shouldContainExactlyInAnyOrder(expected)

                }
                it("村が全て返却される") {
                    // given
                    val village1 = Village(
                        id = VillageId.generate(),
                        name = "村1",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = false,
                        gameMasterUserId = UserId(UUID.fromString(GAME_MASTER_USER_ID_STRING)),
                    )
                    val village2 = Village(
                        id = VillageId.generate(),
                        name = "村2",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = false,
                        gameMasterUserId = UserId(UUID.fromString(GAME_MASTER_USER_ID_STRING)),
                    )
                    val expected = listOf(village1, village2)
                    val saltInput = "salt"
                    val hashedPassword = "passwordHash"

                    // and
                    transaction {
                        expected.forEach { village ->
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
                                it[gameMasterUserId] = UUID.fromString(GAME_MASTER_USER_ID_STRING)
                            }
                        }

                        // when
                        val actual = villageRepository.selectAllVillages()

                        // then
                        actual.shouldContainExactlyInAnyOrder(expected)
                    }
                }
            }
            this.describe("CreateVillage") {
                context("正常系") {
                    it("村が作成される") {
                        // given
                        val village = Village(
                            id = VillageId.generate(),
                            name = "村1",
                            citizenCount = 10,
                            werewolfCount = 2,
                            fortuneTellerCount = 1,
                            knightCount = 1,
                            psychicCount = 1,
                            madmanCount = 1,
                            isInitialActionActive = false,
                            gameMasterUserId = UserId(UUID.fromString(GAME_MASTER_USER_ID_STRING)),
                        )
                        val passwordHash = "passwordHash"
                        val salt = "salt"

                        // when, then
                        shouldNotThrowAny {
                            villageRepository.createVillage(village, passwordHash, salt)
                        }
                    }
                }
                context("異常系") {
                    it("同じIDの村が作成された場合は村が作成されずに例外がthrowされる") {
                        // given
                        val sameVillageId = VillageId.generate()
                        val village = Village(
                            id = sameVillageId,
                            name = "村1",
                            citizenCount = 10,
                            werewolfCount = 2,
                            fortuneTellerCount = 1,
                            knightCount = 1,
                            psychicCount = 1,
                            madmanCount = 1,
                            isInitialActionActive = false,
                            gameMasterUserId = UserId(UUID.fromString(GAME_MASTER_USER_ID_STRING)),
                        )
                        val salt = "salt"
                        val passwordHash = "passwordHash"

                        // when, then
                        shouldNotThrowAny { villageRepository.createVillage(village, passwordHash, salt) }
                        shouldThrowExactly<ExposedSQLException> {
                            villageRepository.createVillage(
                                village,
                                passwordHash,
                                salt,
                            )
                        }
                    }
                    it("UserTableに登録されてないGameMasterUserIdを持つ村が作成された場合は村が作成されずに例外がthrowされる") {
                        // given
                        val notExistGameMasterUserId = UserId(UUID.randomUUID())
                        val village = Village(
                            id = VillageId.generate(),
                            name = "村1",
                            citizenCount = 10,
                            werewolfCount = 2,
                            fortuneTellerCount = 1,
                            knightCount = 1,
                            psychicCount = 1,
                            madmanCount = 1,
                            isInitialActionActive = false,
                            gameMasterUserId = notExistGameMasterUserId,
                        )
                        val salt = "salt"
                        val passwordHash = "passwordHash"

                        // when, then
                        shouldThrowExactly<ExposedSQLException> { villageRepository.createVillage(village, passwordHash, salt) }
                    }
                }
            }
        }
    }

    private companion object {
        const val GAME_MASTER_USER_ID_STRING = "00000000-0000-0000-0000-000000000000"
    }
}

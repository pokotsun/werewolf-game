package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastructure.db.VillageRepositoryImpl
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
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

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        super.afterTest(testCase, result)
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
                            id = VillageId(UUID.randomUUID()),
                            name = "村1",
                            citizenCount = 10,
                            werewolfCount = 2,
                            fortuneTellerCount = 1,
                            knightCount = 1,
                            psychicCount = 1,
                            madmanCount = 1,
                            isInitialActionActive = true,
                        ),
                    )
                    val saltInput = "salt"
                    val hashedPassword = "passwordHash"
                    val expectedSize = expected.size

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
                            }
                        }
                    }

                    // when
                    val actual = villageRepository.selectAllVillages()

                    // then
                    actual.size.shouldBe(expectedSize)
                    actual.shouldBe(expected)
                }
                it("村が全て返却される") {
                    // given
                    val village1 = Village(
                        id = VillageId(UUID.randomUUID()),
                        name = "村1",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = false,
                    )
                    val village2 = Village(
                        id = VillageId(UUID.randomUUID()),
                        name = "村2",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = false,
                    )
                    val expected = listOf(village1, village2)
                    val expectedSize = expected.size
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
                            }
                        }

                        // when
                        val actual = villageRepository.selectAllVillages()

                        // then
                        actual.size.shouldBe(expectedSize)
                        actual.shouldBe(expected)
                    }
                }
            }
            this.describe("CreateVillage") {
                context("正常系") {
                    it("村が作成される") {
                        // given
                        val village = Village(
                            id = VillageId(UUID.randomUUID()),
                            name = "村1",
                            citizenCount = 10,
                            werewolfCount = 2,
                            fortuneTellerCount = 1,
                            knightCount = 1,
                            psychicCount = 1,
                            madmanCount = 1,
                            isInitialActionActive = false,
                        )
                        val passwordHash = "passwordHash"
                        val salt = "salt"

                        // when, then
                        shouldNotThrowAny { villageRepository.createVillage(village, passwordHash, salt) }
                    }
                }
                context("異常系") {
                    it("同じIDの村が作成された場合は村が作成されずに例外がthrowされる") {
                        // given
                        val sameId = VillageId(UUID.randomUUID())
                        val village = Village(
                            id = sameId,
                            name = "村1",
                            citizenCount = 10,
                            werewolfCount = 2,
                            fortuneTellerCount = 1,
                            knightCount = 1,
                            psychicCount = 1,
                            madmanCount = 1,
                            isInitialActionActive = false,
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
                }
            }
        }
    }
}

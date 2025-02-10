package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastructure.db.VillageRepositoryImpl
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.Tuple2
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

    override fun afterTest(f: suspend (Tuple2<TestCase, TestResult>) -> Unit) {
        // 全てのテスト後にVillageTableのデータを初期化する
        super.afterTest(f)
        transaction {
            VillageTable.deleteAll()
        }
    }

    init {
        this.describe("SelectAllVillages") {
            context("正常系") {
                it("村が1つもない場合、空のリストが返却される") {
                    // when
                    val villages = villageRepository.selectAllVillages()

                    // then
                    villages.shouldBeEmpty()
                }
                it("村が1つ返却される") {
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
                    val salt = "salt"
                    val passwordHash = "passwordHash"

                    // and
                    transaction {
                        VillageTable.insert {
                            it[id] = village.id.value
                            it[name] = village.name
                            it[VillageTable.salt] = salt
                            it[VillageTable.passwordHash] = passwordHash
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
                    val villages = villageRepository.selectAllVillages()

                    // then
                    villages.size.shouldBe(1)
                    villages[0].id.shouldBe(village.id)
                    villages[0].name.shouldBe(village.name)
                    villages[0].citizenCount.shouldBe(village.citizenCount)
                    villages[0].werewolfCount.shouldBe(village.werewolfCount)
                    villages[0].fortuneTellerCount.shouldBe(village.fortuneTellerCount)
                    villages[0].knightCount.shouldBe(village.knightCount)
                    villages[0].psychicCount.shouldBe(village.psychicCount)
                    villages[0].madmanCount.shouldBe(village.madmanCount)
                    villages[0].isInitialActionActive.shouldBe(village.isInitialActionActive)
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
                    val salt = "salt"
                    val passwordHash = "passwordHash"

                    // and
                    transaction {
                        VillageTable.insert {
                            it[id] = village1.id.value
                            it[name] = village1.name
                            it[VillageTable.salt] = salt
                            it[VillageTable.passwordHash] = passwordHash
                            it[citizenCount] = village1.citizenCount
                            it[werewolfCount] = village1.werewolfCount
                            it[fortuneTellerCount] = village1.fortuneTellerCount
                            it[knightCount] = village1.knightCount
                            it[psychicCount] = village1.psychicCount
                            it[madmanCount] = village1.madmanCount
                            it[isInitialActionActive] = village1.isInitialActionActive
                        }
                        VillageTable.insert {
                            it[id] = village2.id.value
                            it[name] = village2.name
                            it[VillageTable.salt] = salt
                            it[VillageTable.passwordHash] = passwordHash
                            it[citizenCount] = village2.citizenCount
                            it[werewolfCount] = village2.werewolfCount
                            it[fortuneTellerCount] = village2.fortuneTellerCount
                            it[knightCount] = village2.knightCount
                            it[psychicCount] = village2.psychicCount
                            it[madmanCount] = village2.madmanCount
                            it[isInitialActionActive] = village2.isInitialActionActive
                        }
                    }

                    // when
                    val villages = villageRepository.selectAllVillages()

                    // then
                    villages.size.shouldBe(2)
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

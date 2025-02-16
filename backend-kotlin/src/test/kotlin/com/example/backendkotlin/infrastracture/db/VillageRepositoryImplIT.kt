package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastracture.db.record.UserRecord
import com.example.backendkotlin.infrastracture.db.record.VillageRecord
import com.example.backendkotlin.infrastructure.db.VillageRepositoryImpl
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VillageRepositoryImplIT(
    private val villageRepository: VillageRepositoryImpl,
) : DescribeSpecUsingPostgreSQLTestContainer() {
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
                    val gameMaster = User(
                        id = UserId.generate(),
                        name = "GM",
                        isActive = true,
                    )
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
                            gameMasterUserId = gameMaster.id,
                        ),
                    )
                    val saltInput = "salt"
                    val hashedPassword = "passwordHash"

                    // and
                    UserRecord(gameMaster).insert()
                    expected.forEach { village ->
                        VillageRecord(village, saltInput, hashedPassword).insert()
                    }

                    // when
                    val actual = villageRepository.selectAllVillages()

                    // then
                    actual.shouldContainExactlyInAnyOrder(expected)
                }
                it("村が全て返却される") {
                    // given
                    val gameMaster = User(
                        id = UserId.generate(),
                        name = "GM",
                        isActive = true,
                    )
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
                        gameMasterUserId = gameMaster.id,
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
                        gameMasterUserId = gameMaster.id,
                    )
                    val expected = listOf(village1, village2)
                    val saltInput = "salt"
                    val hashedPassword = "passwordHash"

                    // and
                    UserRecord(gameMaster).insert()
                    expected.forEach { village ->
                        VillageRecord(village, saltInput, hashedPassword).insert()
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
                    val gameMaster = User(
                        id = UserId.generate(),
                        name = "GM",
                        isActive = true,
                    )
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
                        gameMasterUserId = gameMaster.id,
                    )
                    val passwordHash = "passwordHash"
                    val salt = "salt"

                    // and
                    UserRecord(gameMaster).insert()

                    // when, then
                    shouldNotThrowAny {
                        villageRepository.createVillage(village, passwordHash, salt)
                    }
                }
            }
            context("異常系") {
                it("同じIDの村が作成された場合は村が作成されずに例外がthrowされる") {
                    // given
                    val gameMaster = User(
                        id = UserId.generate(),
                        name = "GM",
                        isActive = true,
                    )
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
                        gameMasterUserId = gameMaster.id,
                    )
                    val salt = "salt"
                    val passwordHash = "passwordHash"

                    // and
                    UserRecord(gameMaster).insert()

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
                    val notExistGameMasterUserId = UserId.generate()
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

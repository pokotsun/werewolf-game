package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastracture.db.record.RUserVillageRecord
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
                it("現在のユーザー数を含めて村が1つ返却される") {
                    // given
                    val gameMaster = User(
                        id = UserId.generate(),
                        name = "GM",
                        isActive = true,
                    )
                    val user1 = User(
                        id = UserId.generate(),
                        name = "user1",
                        isActive = true,
                    )
                    val village1Users = listOf(gameMaster, user1)
                    val villageId = VillageId.generate()
                    val expected = listOf(
                        Village(
                            id = villageId,
                            name = "村1",
                            citizenCount = 10,
                            werewolfCount = 2,
                            fortuneTellerCount = 1,
                            knightCount = 1,
                            psychicCount = 1,
                            madmanCount = 1,
                            isInitialActionActive = true,
                            gameMasterUserId = gameMaster.id,
                            currentUserNumber = village1Users.size,
                        ),
                    )

                    val saltInput = "salt"
                    val hashedPassword = "passwordHash"

                    // and
                    UserRecord(gameMaster).insert()
                    UserRecord(user1).insert()
                    expected.forEach { village ->
                        VillageRecord(village, saltInput, hashedPassword).insert()
                    }
                    village1Users.forEach { user ->
                        RUserVillageRecord(user.id, villageId).insert()
                    }
                    // when
                    val actual = villageRepository.selectAllVillages()

                    // then
                    actual.shouldContainExactlyInAnyOrder(expected)
                }
                it("現在のユーザー数を含めて村が全て返却される") {
                    // given
                    val gameMaster = User(
                        id = UserId.generate(),
                        name = "GM",
                        isActive = true,
                    )
                    val village1User = User(
                        id = UserId.generate(),
                        name = "user1",
                        isActive = true,
                    )
                    val village1Users = listOf(gameMaster, village1User)
                    val village1VillageId = VillageId.generate()
                    val village1 = Village(
                        id = village1VillageId,
                        name = "村1",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = false,
                        gameMasterUserId = gameMaster.id,
                        currentUserNumber = village1Users.size,
                    )
                    val village2Users = listOf(gameMaster)
                    val village2VillageId = VillageId.generate()
                    val village2 = Village(
                        id = village2VillageId,
                        name = "村2",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = false,
                        gameMasterUserId = gameMaster.id,
                        currentUserNumber = village2Users.size,
                    )
                    val expected = listOf(village1, village2)
                    val saltInput = "salt"
                    val hashedPassword = "passwordHash"

                    // and
                    UserRecord(gameMaster).insert()
                    UserRecord(village1User).insert()
                    // village2のユーザーはgameMasterのみですでにinsertされている
                    expected.forEach { village ->
                        VillageRecord(village, saltInput, hashedPassword).insert()
                    }
                    village1Users.forEach { user ->
                        RUserVillageRecord(user.id, village1VillageId).insert()
                    }
                    village2Users.forEach { user ->
                        RUserVillageRecord(user.id, village2VillageId).insert()
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

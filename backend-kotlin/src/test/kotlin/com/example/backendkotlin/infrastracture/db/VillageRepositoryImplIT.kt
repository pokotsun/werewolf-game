package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastracture.db.record.RUserVillageRecord
import com.example.backendkotlin.infrastracture.db.record.UserRecord
import com.example.backendkotlin.infrastracture.db.record.VillageRecord
import com.example.backendkotlin.infrastructure.db.VillageRepositoryImpl
import com.example.backendkotlin.util.KSelect
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import org.instancio.Instancio
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
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::isActive), true)
                        .create()
                    val user1 = Instancio.of(User::class.java)
                        .set(KSelect.field(User::isActive), true)
                        .create()
                    val village1Users = listOf(gameMaster, user1)
                    val villageId = Instancio.create(VillageId::class.java)
                    val expected = listOf(
                        Instancio.of(Village::class.java)
                            .set(KSelect.field(Village::id), villageId)
                            .set(KSelect.field(Village::citizenCount), 10)
                            .set(KSelect.field(Village::werewolfCount), 2)
                            .set(KSelect.field(Village::fortuneTellerCount), 1)
                            .set(KSelect.field(Village::knightCount), 1)
                            .set(KSelect.field(Village::psychicCount), 1)
                            .set(KSelect.field(Village::madmanCount), 1)
                            .set(KSelect.field(Village::isInitialActionActive), true)
                            .set(KSelect.field(Village::gameMasterUserId), gameMaster.id)
                            .set(KSelect.field(Village::currentUserNumber), village1Users.size)
                            .set(KSelect.field(Village::isRecruited), true)
                            .create(),
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
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::isActive), true)
                        .create()
                    val village1User = Instancio.of(User::class.java)
                        .set(KSelect.field(User::isActive), true)
                        .create()
                    val village1Users = listOf(gameMaster, village1User)
                    val village1VillageId = Instancio.create(VillageId::class.java)
                    val village1 = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), village1VillageId)
                        .set(KSelect.field(Village::citizenCount), 10)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 1)
                        .set(KSelect.field(Village::isInitialActionActive), false)
                        .set(KSelect.field(Village::gameMasterUserId), gameMaster.id)
                        .set(KSelect.field(Village::currentUserNumber), village1Users.size)
                        .set(KSelect.field(Village::isRecruited), true)
                        .create()

                    val village2Users = listOf(gameMaster)
                    val village2VillageId = Instancio.create(VillageId::class.java)
                    val village2 = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), village2VillageId)
                        .set(KSelect.field(Village::citizenCount), 10)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 1)
                        .set(KSelect.field(Village::isInitialActionActive), false)
                        .set(KSelect.field(Village::gameMasterUserId), gameMaster.id)
                        .set(KSelect.field(Village::currentUserNumber), village2Users.size)
                        .set(KSelect.field(Village::isRecruited), true)
                        .create()
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
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::isActive), true)
                        .create()
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::citizenCount), 10)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 1)
                        .set(KSelect.field(Village::isInitialActionActive), false)
                        .set(KSelect.field(Village::gameMasterUserId), gameMaster.id)
                        .create()
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
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::isActive), true)
                        .create()
                    val sameVillageId = Instancio.create(VillageId::class.java)
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), sameVillageId)
                        .set(KSelect.field(Village::citizenCount), 10)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 1)
                        .set(KSelect.field(Village::isInitialActionActive), false)
                        .set(KSelect.field(Village::gameMasterUserId), gameMaster.id)
                        .create()
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
                    val notExistGameMasterUserId = Instancio.create(UserId::class.java)
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::citizenCount), 10)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 1)
                        .set(KSelect.field(Village::isInitialActionActive), false)
                        .set(KSelect.field(Village::gameMasterUserId), notExistGameMasterUserId)
                        .create()

                    val salt = "salt"
                    val passwordHash = "passwordHash"

                    // when, then
                    shouldThrowExactly<ExposedSQLException> { villageRepository.createVillage(village, passwordHash, salt) }
                }
            }
        }
    }
}

package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.RUserVillageRepository
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.UserRepository
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.util.KSelect
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.Tuple2
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkObject
import io.mockk.unmockkStatic
import io.mockk.verify
import org.instancio.Instancio
import org.springframework.security.crypto.bcrypt.BCrypt

/**
 * CreateVillageUseCaseのテストクラス
 */
class CreateVillageUseCaseUT(
    @MockkBean
    private val villageRepository: VillageRepository,

    @MockkBean
    private val userRepository: UserRepository,

    @MockkBean
    private val rUserVillageRepository: RUserVillageRepository,
) : DescribeSpec() {
    @InjectMockKs
    private lateinit var target: CreateVillageUseCase

    override suspend fun beforeTest(testCase: TestCase) {
        mockkStatic(BCrypt::class)
        mockkObject(UserId, VillageId)
    }

    override fun afterTest(f: suspend (Tuple2<TestCase, TestResult>) -> Unit) {
        // テスト後にMockの挙動を初期化する
        confirmVerified(
            villageRepository,
            userRepository,
            rUserVillageRepository,
        )
        clearAllMocks()
        unmockkStatic(BCrypt::class)
        unmockkObject(UserId, VillageId)
    }

    init {
        this.describe("invokeメソッドのテスト") {
            context("正常系") {
                it("村を作成する") {
                    // given
                    val gameMasterId = Instancio.create(UserId::class.java)
                    val gameMasterName = "gameMaster"
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), gameMasterId)
                        .set(KSelect.field(User::name), gameMasterName)
                        .set(KSelect.field(User::isActive), true)
                        .create()

                    val villageId = Instancio.create(VillageId::class.java)
                    val villageName = "村1"
                    val villageCitizenCount = 10
                    val villageWerewolfCount = 2
                    val villageFortuneTellerCount = 1
                    val villageKnightCount = 1
                    val villagePsychicCount = 1
                    val villageMadmanCount = 1
                    val villageIsInitialActionActive = true
                    val expected = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .set(KSelect.field(Village::name), villageName)
                        .set(KSelect.field(Village::citizenCount), villageCitizenCount)
                        .set(KSelect.field(Village::werewolfCount), villageWerewolfCount)
                        .set(KSelect.field(Village::fortuneTellerCount), villageFortuneTellerCount)
                        .set(KSelect.field(Village::knightCount), villageKnightCount)
                        .set(KSelect.field(Village::psychicCount), villagePsychicCount)
                        .set(KSelect.field(Village::madmanCount), villageMadmanCount)
                        .set(KSelect.field(Village::isInitialActionActive), villageIsInitialActionActive)
                        .set(KSelect.field(Village::gameMasterUserId), gameMasterId)
                        .set(KSelect.field(Village::currentUserNumber), 1)
                        .create()
                    val expected2 = Village(
                        id = villageId,
                        name = villageName,
                        citizenCount = villageCitizenCount,
                        werewolfCount = villageWerewolfCount,
                        fortuneTellerCount = villageFortuneTellerCount,
                        knightCount = villageKnightCount,
                        psychicCount = villagePsychicCount,
                        madmanCount = villageMadmanCount,
                        isInitialActionActive = villageIsInitialActionActive,
                        gameMasterUserId = gameMasterId,
                    )

                    expected shouldBe expected2

                    val password = "password"
                    val salt = "salt"
                    val hashedPassword = "hashedPassword"

                    // and
                    every { BCrypt.gensalt() } returns salt
                    every { BCrypt.hashpw(password, salt) } returns hashedPassword
                    every { BCrypt.checkpw(password, hashedPassword) } returns true
                    every { UserId.generate() } returns gameMasterId
                    every { userRepository.createUser(gameMaster) } returns gameMaster
                    every { VillageId.generate() } returns villageId
                    every { villageRepository.createVillage(any(), hashedPassword, salt) } returns expected
                    every { rUserVillageRepository.save(gameMaster.id, expected.id) } returns Pair(gameMaster.id, expected.id)

                    // when
                    val actual = target.invoke(
                        gameMasterName = gameMasterName,
                        villageName = villageName,
                        villageCitizenCount = villageCitizenCount,
                        villageWerewolfCount = villageWerewolfCount,
                        villageFortuneTellerCount = villageFortuneTellerCount,
                        villageKnightCount = villageKnightCount,
                        villagePsychicCount = villagePsychicCount,
                        villageMadmanCount = villageMadmanCount,
                        villageIsInitialActionActive = villageIsInitialActionActive,
                        villagePassword = password,
                    )

                    // then
                    actual shouldBe expected
                    verify(exactly = 1) {
                        userRepository.createUser(gameMaster)
                        villageRepository.createVillage(expected, hashedPassword, salt)
                        rUserVillageRepository.save(gameMaster.id, expected.id)
                    }
                }
            }
            context("異常系") {
                it("パスワードの暗号化に失敗する") {
                    // given
                    val gameMasterId = Instancio.create(UserId::class.java)
                    val gameMasterName = "gameMaster"
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), gameMasterId)
                        .set(KSelect.field(User::name), gameMasterName)
                        .set(KSelect.field(User::isActive), true)
                        .create()

                    val villageId = Instancio.create(VillageId::class.java)
                    val villageName = "村1"
                    val villageCitizenCount = 10
                    val villageWerewolfCount = 2
                    val villageFortuneTellerCount = 1
                    val villageKnightCount = 1
                    val villagePsychicCount = 1
                    val villageMadmanCount = 1
                    val villageIsInitialActionActive = true
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .set(KSelect.field(Village::name), villageName)
                        .set(KSelect.field(Village::citizenCount), villageCitizenCount)
                        .set(KSelect.field(Village::werewolfCount), villageWerewolfCount)
                        .set(KSelect.field(Village::fortuneTellerCount), villageFortuneTellerCount)
                        .set(KSelect.field(Village::knightCount), villageKnightCount)
                        .set(KSelect.field(Village::psychicCount), villagePsychicCount)
                        .set(KSelect.field(Village::madmanCount), villageMadmanCount)
                        .set(KSelect.field(Village::isInitialActionActive), villageIsInitialActionActive)
                        .set(KSelect.field(Village::gameMasterUserId), gameMasterId)
                        .set(KSelect.field(Village::currentUserNumber), 1)
                        .create()

                    val password = "password"
                    val salt = "salt"
                    val hashedPassword = "hashedPassword"

                    // and
                    every { UserId.generate() } returns gameMasterId
                    every { userRepository.createUser(gameMaster) } returns gameMaster
                    every { BCrypt.gensalt() } returns salt
                    every { BCrypt.hashpw(password, salt) } returns hashedPassword
                    every { BCrypt.checkpw(password, hashedPassword) } returns false

                    // when, then
                    val exception = shouldThrowExactly<RuntimeException> {
                        target.invoke(
                            gameMasterName = gameMaster.name,
                            villageName = village.name,
                            villageCitizenCount = village.citizenCount,
                            villageWerewolfCount = village.werewolfCount,
                            villageFortuneTellerCount = village.fortuneTellerCount,
                            villageKnightCount = village.knightCount,
                            villagePsychicCount = village.psychicCount,
                            villageMadmanCount = village.madmanCount,
                            villageIsInitialActionActive = village.isInitialActionActive,
                            villagePassword = password,
                        )
                    }

                    // then
                    exception.message shouldBe "Password encryption failed"
                    verify(exactly = 1) { userRepository.createUser(gameMaster) }
                    verify(exactly = 0) {
                        villageRepository.createVillage(village, hashedPassword, salt)
                        rUserVillageRepository.save(gameMaster.id, village.id)
                    }
                }
            }
        }
    }
}

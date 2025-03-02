package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.RUserVillageRepository
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.UserRepository
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.domain.WerewolfErrorCode
import com.example.backendkotlin.domain.WerewolfException
import com.example.backendkotlin.util.KSelect
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.instancio.Instancio

/**
 * EnterVillageUseCaseのテストクラス
 */
class EnterVillageUseCaseUT(
    @MockkBean
    private val villageRepository: VillageRepository,

    @MockkBean
    private val userRepository: UserRepository,

    @MockkBean
    private val rUserVillageRepository: RUserVillageRepository,
) : DescribeSpec() {
    @InjectMockKs
    private lateinit var target: EnterVillageUseCase

    override suspend fun beforeSpec(spec: Spec) {
        mockkObject(UserId, VillageId, HashedPassword)
    }

    override suspend fun afterSpec(spec: Spec) {
        unmockkObject(UserId, VillageId, HashedPassword)
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        // テスト後にMockの挙動を初期化する
        confirmVerified(
            villageRepository,
            userRepository,
            rUserVillageRepository,
        )
        clearAllMocks()
    }

    init {
        this.describe("invokeメソッドのテスト") {
            context("正常系") {
                it("村にログインする") {
                    // given
                    val gameMaserUserId = Instancio.create(UserId::class.java)

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
                        .set(KSelect.field(Village::gameMasterUserId), gameMaserUserId)
                        .set(KSelect.field(Village::currentUserNumber), 1)
                        .set(KSelect.field(Village::isRecruited), true)
                        .create()

                    val villagePassword = "village-password"
                    val villageHashedPassword = Instancio.create(HashedPassword::class.java)
                    val villageAndHashedPassword = Pair(village, villageHashedPassword)

                    val newUserId = Instancio.create(UserId::class.java)
                    val newUserName = "userName"
                    val newUser = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), newUserId)
                        .set(KSelect.field(User::name), newUserName)
                        .set(KSelect.field(User::isActive), true)
                        .create()
                    val newUserPassword = "user-password"
                    val newUserHashedPassword = Instancio.create(HashedPassword::class.java)

                    val expected = Pair(newUserId, villageId)

                    // and
                    every { VillageId.generate(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageById(villageId) } returns villageAndHashedPassword
                    every { HashedPassword.doesMatch(villagePassword, villageHashedPassword) } returns true
                    every { UserId.generate() } returns newUserId
                    every { HashedPassword.create(newUserPassword) } returns newUserHashedPassword
                    every { userRepository.createUser(newUser, newUserHashedPassword) } returns newUser
                    every { rUserVillageRepository.save(newUserId, villageId) } returns expected

                    // when
                    val actual = target.invoke(villageId.value.toString(), villagePassword, newUserName, newUserPassword)

                    // then
                    actual shouldBe expected
                    verify(exactly = 1) {
                        villageRepository.selectVillageById(villageId)
                        HashedPassword.doesMatch(villagePassword, villageHashedPassword)
                        UserId.generate()
                        HashedPassword.create(newUserPassword)
                        userRepository.createUser(newUser, newUserHashedPassword)
                        rUserVillageRepository.save(newUserId, villageId)
                    }
                }
            }
            context("異常系") {
                it("村が存在しない") {
                    // given
                    val villageId = Instancio.create(VillageId::class.java)

                    // and
                    every { VillageId.generate(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageById(villageId) } returns null

                    // when, then
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(villageId.value.toString(), "password", "userName", "password")
                    }
                    exception.message shouldBe "村が存在しません"

                    verify(exactly = 1) {
                        villageRepository.selectVillageById(villageId)
                    }
                    verify(exactly = 0) {
                        HashedPassword.doesMatch(any(), any())
                        UserId.generate()
                        HashedPassword.create(any())
                        userRepository.createUser(any(), any())
                        rUserVillageRepository.save(any(), any())
                    }
                }
                it("パスワードが違う") {
                    // given
                    val villageId = Instancio.create(VillageId::class.java)
                    val village = Instancio.create(Village::class.java)
                    val incorrectVillagePassword = "password"
                    val villageHashedPassword = Instancio.create(HashedPassword::class.java)
                    val villageAndHashedPassword = Pair(village, villageHashedPassword)

                    // and
                    every { VillageId.generate(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageById(villageId) } returns villageAndHashedPassword
                    every { HashedPassword.doesMatch(incorrectVillagePassword, villageHashedPassword) } returns false

                    // when, then
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(villageId.value.toString(), incorrectVillagePassword, "userName", "password")
                    }

                    // then
                    exception.code shouldBe WerewolfErrorCode.VILLAGE_PASSWORD_IS_WRONG
                    exception.message shouldBe "村のパスワードが違います"

                    verify(exactly = 1) {
                        villageRepository.selectVillageById(villageId)
                        HashedPassword.doesMatch(incorrectVillagePassword, villageHashedPassword)
                    }
                    verify(exactly = 0) {
                        UserId.generate()
                        HashedPassword.create(any())
                        userRepository.createUser(any(), any())
                        rUserVillageRepository.save(any(), any())
                    }
                }
            }
        }
    }
}

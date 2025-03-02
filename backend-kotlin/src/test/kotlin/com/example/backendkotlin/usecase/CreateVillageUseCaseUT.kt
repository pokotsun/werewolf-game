package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.RUserVillageRepository
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.UserRepository
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.util.KSelect
import com.ninjasquad.springmockk.MockkBean
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
import io.mockk.unmockkObject
import io.mockk.verify
import org.instancio.Instancio

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
        mockkObject(UserId, VillageId, HashedPassword)
    }

    override fun afterTest(f: suspend (Tuple2<TestCase, TestResult>) -> Unit) {
        // テスト後にMockの挙動を初期化する
        confirmVerified(
            villageRepository,
            userRepository,
            rUserVillageRepository,
        )
        clearAllMocks()
        unmockkObject(UserId, VillageId, HashedPassword)
    }

    init {
        this.describe("invokeメソッドのテスト") {
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
                    .set(KSelect.field(Village::isRecruited), true)
                    .create()

                val gameMasterPassword = "game-master-password"
                val gameMasterHashedPassword = Instancio.create(HashedPassword::class.java)
                val villagePassword = "village-password"
                val villageHashedPassword = Instancio.create(HashedPassword::class.java)

                // and
                every { UserId.generate() } returns gameMasterId
                every { HashedPassword.create(gameMasterPassword) } returns gameMasterHashedPassword
                every { HashedPassword.create(villagePassword) } returns villageHashedPassword
                every { userRepository.createUser(gameMaster, gameMasterHashedPassword) } returns gameMaster
                every { VillageId.generate() } returns villageId
                every { villageRepository.createVillage(expected, villageHashedPassword) } returns expected
                every { rUserVillageRepository.save(gameMaster.id, expected.id) } returns Pair(gameMaster.id, expected.id)

                // when
                val actual = target.invoke(
                    gameMasterName = gameMasterName,
                    gameMasterPassword = gameMasterPassword,
                    villageName = villageName,
                    villageCitizenCount = villageCitizenCount,
                    villageWerewolfCount = villageWerewolfCount,
                    villageFortuneTellerCount = villageFortuneTellerCount,
                    villageKnightCount = villageKnightCount,
                    villagePsychicCount = villagePsychicCount,
                    villageMadmanCount = villageMadmanCount,
                    villageIsInitialActionActive = villageIsInitialActionActive,
                    villagePassword = villagePassword,
                )

                // then
                actual shouldBe expected
                verify(exactly = 1) {
                    userRepository.createUser(gameMaster, gameMasterHashedPassword)
                    villageRepository.createVillage(expected, villageHashedPassword)
                    rUserVillageRepository.save(gameMaster.id, expected.id)
                }
            }
        }
    }
}

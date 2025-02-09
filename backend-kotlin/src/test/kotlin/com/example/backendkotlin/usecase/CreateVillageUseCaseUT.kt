package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.RUserVillageRepository
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.UserRepository
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
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
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import org.springframework.security.crypto.bcrypt.BCrypt
import java.util.UUID

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

    override fun afterTest(f: suspend (Tuple2<TestCase, TestResult>) -> Unit) {
        // テスト後にMockの挙動を初期化する
        confirmVerified(villageRepository)
        confirmVerified(userRepository)
        confirmVerified(rUserVillageRepository)
        clearAllMocks()
        unmockkStatic(BCrypt::class)
    }

    init {
        this.describe("invokeメソッドのテスト") {
            context("正常系") {
                it("村を作成する") {
                    // given
                    val gameMaster = User(
                        id = UserId(UUID.randomUUID()),
                        name = "gameMaster",
                        isActive = true,
                    )
                    val village = Village(
                        id = VillageId(UUID.randomUUID()),
                        name = "村1",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = true,
                        gameMasterUserId = gameMaster.id,
                    )
                    val expected = village.copy()
                    val password = "password"
                    val salt = "salt"
                    val hashedPassword = "hashedPassword"

                    // and
                    mockkStatic(BCrypt::class)
                    every { BCrypt.gensalt() } returns salt
                    every { BCrypt.hashpw(password, salt) } returns hashedPassword
                    every { BCrypt.checkpw(password, hashedPassword) } returns true
                    every { villageRepository.createVillage(village, hashedPassword, salt) } returns expected
                    every { rUserVillageRepository.save(gameMaster.id, village.id) } returns Pair(gameMaster.id, expected.id)

                    // when
                    val actual = target.invoke(village, password, gameMaster)

                    // then
                    actual shouldBe expected
                    verify(exactly = 1) { userRepository.createUser(gameMaster) }
                    verify(exactly = 1) { villageRepository.createVillage(village, hashedPassword, salt) }
                    verify(exactly = 1) { rUserVillageRepository.save(gameMaster.id, village.id) }
                }
            }
            context("異常系") {
                it("ゲームマスターが作成されたが、IDが一致しない") {
                    // given
                    val requestedGameMaster = User(
                        id = UserId(UUID.randomUUID()),
                        name = "gameMaster",
                        isActive = true,
                    )
                    val village = Village(
                        id = VillageId(UUID.randomUUID()),
                        name = "村1",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = true,
                        gameMasterUserId = requestedGameMaster.id,
                    )
                    val password = "password"
                    val salt = "salt"
                    val hashedPassword = "hashedPassword"
                    val expectedExceptionMessage = "Password encryption failed"

                    // and
                    // idの異なるゲームマスターが作成されたとする
                    val createdGameMaster = User(
                        id = UserId(UUID.randomUUID()),
                        name = "gameMaster",
                        isActive = true,
                    )
                    every { userRepository.createUser(requestedGameMaster) } returns createdGameMaster
                    mockkStatic(BCrypt::class)
                    every { BCrypt.gensalt() } returns salt
                    every { BCrypt.hashpw(password, salt) } returns hashedPassword
                    every { BCrypt.checkpw(password, hashedPassword) } returns false
                    every { villageRepository.createVillage(village, hashedPassword, salt) } returns village
                    every { rUserVillageRepository.save(createdGameMaster.id, village.id) } returns Pair(createdGameMaster.id, village.id)

                    // when, then
                    val exception = shouldThrowExactly<RuntimeException> {
                        target.invoke(village, password, requestedGameMaster)
                    }

                    // then
                    exception.message shouldBe expectedExceptionMessage
                    verify(exactly = 1) { userRepository.createUser(requestedGameMaster) }
                    verify(exactly = 0) { villageRepository.createVillage(village, hashedPassword, salt) }
                    verify(exactly = 0) { rUserVillageRepository.save(createdGameMaster.id, village.id) }
                }
                it("パスワードの暗号化に失敗する") {
                    // given
                    val gameMaster = User(
                        id = UserId(UUID.randomUUID()),
                        name = "gameMaster",
                        isActive = true,
                    )
                    val village = Village(
                        id = VillageId(UUID.randomUUID()),
                        name = "村1",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = true,
                        gameMasterUserId = gameMaster.id,
                    )
                    val password = "password"
                    val salt = "salt"
                    val hashedPassword = "hashedPassword"

                    // and
                    every { userRepository.createUser(gameMaster) } returns gameMaster
                    mockkStatic(BCrypt::class)
                    every { BCrypt.gensalt() } returns salt
                    every { BCrypt.hashpw(password, salt) } returns hashedPassword
                    every { BCrypt.checkpw(password, hashedPassword) } returns false
                    every { villageRepository.createVillage(village, hashedPassword, salt) } returns village
                    every { rUserVillageRepository.save(gameMaster.id, village.id) } returns Pair(gameMaster.id, village.id)

                    // when, then
                    val exception = shouldThrowExactly<RuntimeException> {
                        target.invoke(village, password, gameMaster)
                    }

                    // then
                    exception.message shouldBe "Password encryption failed"
                    verify(exactly = 1) { userRepository.createUser(gameMaster) }
                    verify(exactly = 0) { villageRepository.createVillage(village, hashedPassword, salt) }
                    verify(exactly = 0) { rUserVillageRepository.save(gameMaster.id, village.id) }
                }
            }
        }
    }
}

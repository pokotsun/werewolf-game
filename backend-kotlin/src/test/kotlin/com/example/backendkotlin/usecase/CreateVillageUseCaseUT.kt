package com.example.backendkotlin.usecase

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
import org.springframework.security.crypto.bcrypt.BCrypt
import java.util.UUID

/**
 * CreateVillageUseCaseのテストクラス
 */
class CreateVillageUseCaseUT(
    @MockkBean
    private val villageRepository: VillageRepository,
) : DescribeSpec() {
    @InjectMockKs
    private lateinit var target: CreateVillageUseCase

    override fun afterTest(f: suspend (Tuple2<TestCase, TestResult>) -> Unit) {
        // テスト後にMockの挙動を初期化する
        confirmVerified(villageRepository)
        clearAllMocks()
        unmockkStatic(BCrypt::class)
    }

    init {
        this.describe("invokeメソッドのテスト") {
            context("正常系") {
                it("村を作成する") {
                    // given
                    val expected = Village(
                        id = VillageId(UUID.randomUUID()),
                        name = "村1",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = true,
                    )
                    val password = "password"

                    // and
                    mockkStatic(BCrypt::class)
                    every { BCrypt.gensalt() } returns "salt"
                    every { BCrypt.hashpw(password, "salt") } returns "hashedPassword"
                    every { BCrypt.checkpw(password, "hashedPassword") } returns true
                    every { villageRepository.createVillage(expected, "hashedPassword", "salt") } returns expected

                    // when
                    val actual = target.invoke(expected, password)

                    // then
                    actual shouldBe expected
                }
            }
            context("異常系") {
                it("パスワードの暗号化に失敗する") {
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
                        isInitialActionActive = true,
                    )
                    val password = "password"
                    val salt = "salt"
                    val hashedPassword = "hashedPassword"
                    val expectedExceptionMessage = "Password encryption failed"

                    // and
                    mockkStatic(BCrypt::class)
                    every { BCrypt.gensalt() } returns salt
                    every { BCrypt.hashpw(password, salt) } returns hashedPassword
                    every { BCrypt.checkpw(password, hashedPassword) } returns false

                    // when, then
                    val exception = shouldThrowExactly<RuntimeException> {
                        target.invoke(village, password)
                    }
                    exception.message shouldBe expectedExceptionMessage
                }
            }
        }
    }
}

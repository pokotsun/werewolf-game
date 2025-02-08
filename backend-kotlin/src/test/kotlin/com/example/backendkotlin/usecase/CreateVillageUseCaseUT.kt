package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.springframework.security.crypto.bcrypt.BCrypt
import java.util.UUID

class CreateVillageUseCaseUT : DescribeSpec() {
    private val villageRepository = mockk<VillageRepository>()
    private val target = CreateVillageUseCase(villageRepository)

    init {
        this.describe("invokeメソッドのテスト") {
            context("正常系") {
                it("村を作成する") {
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

                    // and
                    every { villageRepository.createVillage(village, any(), any()) } returns village

                    // when
                    val result = target.invoke(village, password)

                    // then
                    result shouldBe village
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

                    // and
                    mockkStatic(BCrypt::class)
                    every { BCrypt.gensalt() } returns "salt"
                    every { BCrypt.hashpw(password, "salt") } returns "hashedPassword"
                    every { BCrypt.checkpw(password, "hashedPassword") } returns false

                    // when
                    val exception = shouldThrowExactly<RuntimeException> {
                        target.invoke(village, password)
                    }
                    exception.message shouldBe "Password encryption failed"
                }
            }
        }
    }
}

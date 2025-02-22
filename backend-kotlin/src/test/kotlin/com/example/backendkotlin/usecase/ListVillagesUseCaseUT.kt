package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
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
import io.mockk.verify

/**
 * ListVillagesUseCaseのテストクラス
 */
class ListVillagesUseCaseUT(
    @MockkBean
    private val villageRepository: VillageRepository,
) : DescribeSpec() {
    @InjectMockKs
    private lateinit var target: ListVillagesUseCase

    override fun afterTest(f: suspend (Tuple2<TestCase, TestResult>) -> Unit) {
        // テスト後にMockの挙動を初期化する
        confirmVerified(villageRepository)
        clearAllMocks()
    }

    init {
        this.describe("invokeメソッドのテスト") {
            context("正常系") {
                it("全ての村が取得できる") {
                    // given
                    val village1GameMasterId = UserId.generate()
                    val village1 = Village(
                        id = VillageId.generate(),
                        name = "村1",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = true,
                        gameMasterUserId = village1GameMasterId,
                        currentUserNumber = 2,
                    )
                    val village2GameMasterId = UserId.generate()
                    val village2 = Village(
                        id = VillageId.generate(),
                        name = "村2",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = true,
                        gameMasterUserId = village2GameMasterId,
                        currentUserNumber = 2,
                    )
                    val expected = listOf(village1, village2)

                    // and
                    every { villageRepository.selectAllVillages() } returns expected

                    // when
                    val actual = target.invoke()

                    // then
                    actual shouldBe expected
                    verify(exactly = 1) {
                        villageRepository.selectAllVillages()
                    }
                }
            }
        }
    }
}

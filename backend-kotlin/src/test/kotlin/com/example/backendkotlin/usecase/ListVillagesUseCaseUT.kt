package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.UUID

/**
 * ListVillagesUseCaseのテストクラス
 */
class ListVillagesUseCaseUT : DescribeSpec() {
    private val villageRepository = mockk<VillageRepository>()
    private val target = ListVillagesUseCase(villageRepository)
    init {
        this.describe("invokeメソッドのテスト") {
            context("正常系") {
                it("全ての村を取得する") {
                    // given
                    val expected = listOf(
                        Village(
                            id = VillageId(UUID.randomUUID()),
                            name = "村1",
                            citizenCount = 10,
                            werewolfCount = 2,
                            fortuneTellerCount = 1,
                            knightCount = 1,
                            psychicCount = 1,
                            madmanCount = 1,
                            isInitialActionActive = true,
                        ),
                        Village(
                            id = VillageId(UUID.randomUUID()),
                            name = "村2",
                            citizenCount = 10,
                            werewolfCount = 2,
                            fortuneTellerCount = 1,
                            knightCount = 1,
                            psychicCount = 1,
                            madmanCount = 1,
                            isInitialActionActive = true,
                        ),
                    )

                    // and
                    every { villageRepository.selectAllVillages() } returns expected

                    // when
                    val result = target.invoke()

                    // then
                    result shouldBe expected
                }
            }
        }
    }
}

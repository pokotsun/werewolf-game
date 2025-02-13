package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.RUserVillageRepository
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

    @MockkBean
    private val rUserVillageRepository: RUserVillageRepository,
) : DescribeSpec() {
    @InjectMockKs
    private lateinit var target: ListVillagesUseCase

    override fun afterTest(f: suspend (Tuple2<TestCase, TestResult>) -> Unit) {
        // テスト後にMockの挙動を初期化する
        confirmVerified(villageRepository)
        confirmVerified(rUserVillageRepository)
        clearAllMocks()
    }

    init {
        this.describe("invokeメソッドのテスト") {
            context("正常系") {
                it("全ての村を取得し現在の参加者数が正しく設定されている") {
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
                    )
                    val village1UserId1 = UserId.generate()
                    val village2UserId1 = UserId.generate()
                    val defaultVillages = listOf(village1, village2)
                    val villageIds = defaultVillages.map { it.id }
                    val userVillageIdMap = mapOf(
                        village1.id to listOf(village1GameMasterId, village1UserId1),
                        village2.id to listOf(village1GameMasterId, village2UserId1),
                    )

                    // and
                    every { villageRepository.selectAllVillages() } returns defaultVillages
                    every { rUserVillageRepository.selectByVillageIds(villageIds) } returns userVillageIdMap
                    val expected = listOf(
                        Village(
                            id = village1.id,
                            name = village1.name,
                            citizenCount = village1.citizenCount,
                            werewolfCount = village1.werewolfCount,
                            fortuneTellerCount = village1.fortuneTellerCount,
                            knightCount = village1.knightCount,
                            psychicCount = village1.psychicCount,
                            madmanCount = village1.madmanCount,
                            isInitialActionActive = village1.isInitialActionActive,
                            gameMasterUserId = village1.gameMasterUserId,
                            currentUserNumber = 2,
                        ),
                        Village(
                            id = village2.id,
                            name = village2.name,
                            citizenCount = village2.citizenCount,
                            werewolfCount = village2.werewolfCount,
                            fortuneTellerCount = village2.fortuneTellerCount,
                            knightCount = village2.knightCount,
                            psychicCount = village2.psychicCount,
                            madmanCount = village2.madmanCount,
                            isInitialActionActive = village2.isInitialActionActive,
                            gameMasterUserId = village2.gameMasterUserId,
                            currentUserNumber = 2,
                        ),
                    )

                    // when
                    val actual = target.invoke()

                    // then
                    actual shouldBe expected
                    verify(exactly = 1) {
                        villageRepository.selectAllVillages()
                        rUserVillageRepository.selectByVillageIds(villageIds)
                    }
                }
            }
            context("異常系") {
                it("RUserVillageへの登録漏れがある場合はデフォルトの値を返す") {
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
                    )
                    val defaultVillages = listOf(village1)
                    val villageIds = defaultVillages.map { it.id }
                    val userVillageIdMap = emptyMap<VillageId, List<UserId>>()

                    // and
                    every { villageRepository.selectAllVillages() } returns defaultVillages
                    every { rUserVillageRepository.selectByVillageIds(villageIds) } returns userVillageIdMap
                    val expected = listOf(
                        Village(
                            id = village1.id,
                            name = village1.name,
                            citizenCount = village1.citizenCount,
                            werewolfCount = village1.werewolfCount,
                            fortuneTellerCount = village1.fortuneTellerCount,
                            knightCount = village1.knightCount,
                            psychicCount = village1.psychicCount,
                            madmanCount = village1.madmanCount,
                            isInitialActionActive = village1.isInitialActionActive,
                            gameMasterUserId = village1.gameMasterUserId,
                        ),
                    )

                    // when
                    val actual = target.invoke()

                    // then
                    actual shouldBe expected
                    verify(exactly = 1) {
                        villageRepository.selectAllVillages()
                        rUserVillageRepository.selectByVillageIds(villageIds)
                    }
                }
            }
        }
    }
}

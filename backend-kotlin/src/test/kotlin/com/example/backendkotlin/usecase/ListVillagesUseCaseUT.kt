package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
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
import io.mockk.verify
import org.instancio.Instancio

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
                    val village1GameMasterId = Instancio.create(UserId::class.java)
                    val village1 = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::citizenCount), 10)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 1)
                        .set(KSelect.field(Village::isInitialActionActive), true)
                        .set(KSelect.field(Village::gameMasterUserId), village1GameMasterId)
                        .set(KSelect.field(Village::currentUserNumber), 2)
                        .create()

                    val village2GameMasterId = Instancio.create(UserId::class.java)
                    val village2 = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::citizenCount), 10)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 1)
                        .set(KSelect.field(Village::isInitialActionActive), true)
                        .set(KSelect.field(Village::gameMasterUserId), village2GameMasterId)
                        .set(KSelect.field(Village::currentUserNumber), 2)
                        .create()
                    val expected = listOf(village1, village2)
                    val isRecruitedOnly = false

                    // and
                    every { villageRepository.selectAllVillages(isRecruitedOnly) } returns expected

                    // when
                    val actual = target.invoke(isRecruitedOnly)

                    // then
                    actual shouldBe expected
                    verify(exactly = 1) {
                        villageRepository.selectAllVillages(isRecruitedOnly)
                    }
                }
            }
        }
    }
}

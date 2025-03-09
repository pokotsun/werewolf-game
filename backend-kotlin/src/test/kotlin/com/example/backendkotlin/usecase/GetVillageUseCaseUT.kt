package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.domain.WerewolfErrorCode
import com.example.backendkotlin.domain.WerewolfException
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
import org.instancio.Instancio

/**
 * GetVillageUseCaseのテストクラス
 */
class GetVillageUseCaseUT(
    @MockkBean
    private val villageRepository: VillageRepository,
) : DescribeSpec() {
    @InjectMockKs
    private lateinit var target: GetVillageUseCase

    override suspend fun beforeSpec(spec: Spec) {
        mockkObject(VillageId)
    }

    override suspend fun afterSpec(spec: Spec) {
        unmockkObject(VillageId)
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        // テスト後にMockの挙動を初期化する
        clearAllMocks()
        confirmVerified(
            villageRepository,
        )
    }

    init {
        this.describe("invokeメソッドのテスト") {
            context("正常系") {
                it("村が存在する場合、村を取得する") {
                    // given
                    val villageIdString = "00000000-0000-0000-0000-000000000000"
                    val villageId = Instancio.create(VillageId::class.java)
                    val village = Instancio.of(Village::class.java).create()
                    val villageHashedPassword = Instancio.create(HashedPassword::class.java)
                    every { VillageId.generate(villageIdString) } returns villageId
                    every { villageRepository.selectVillageById(villageId) } returns Pair(village, villageHashedPassword)

                    // when
                    val result = target.invoke(villageIdString)

                    // then
                    result shouldBe village
                }
            }

            context("異常系") {
                it("村が存在しない場合、ResourceNotFoundExceptionをスローする") {
                    // given
                    val villageIdString = "00000000-0000-0000-0000-000000000000"
                    val villageId = Instancio.create(VillageId::class.java)
                    every { VillageId.generate(villageIdString) } returns villageId
                    every { villageRepository.selectVillageById(villageId) } returns null

                    // when, then
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(villageIdString)
                    }
                    exception.code shouldBe WerewolfErrorCode.RESOURCE_NOT_FOUND
                    exception.message shouldBe "村が存在しません"
                }
            }
        }
    }
}

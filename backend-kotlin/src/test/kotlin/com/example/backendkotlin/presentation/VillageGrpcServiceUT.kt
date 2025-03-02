package com.example.backendkotlin.presentation

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.generated.grpc.CreateVillageRequest
import com.example.backendkotlin.generated.grpc.CreateVillageResponse
import com.example.backendkotlin.generated.grpc.ListVillagesRequest
import com.example.backendkotlin.generated.grpc.ListVillagesResponse
import com.example.backendkotlin.usecase.CreateVillageUseCase
import com.example.backendkotlin.usecase.ListVillagesUseCase
import com.ninjasquad.springmockk.MockkBean
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.stub.StreamObserver
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.spyk
import io.mockk.verify
import org.instancio.Instancio

class VillageGrpcServiceUT(
    @MockkBean
    private val listVillagesUseCase: ListVillagesUseCase,
    @MockkBean
    private val createVillageUseCase: CreateVillageUseCase,
) : DescribeSpec() {
    @InjectMockKs
    private lateinit var service: VillageGrpcService

    override suspend fun beforeTest(testCase: TestCase) {
        MockKAnnotations.init(this)
        clearAllMocks()
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        confirmVerified(
            listVillagesUseCase,
            createVillageUseCase,
        )
    }

    init {
        this.describe("createVillage") {
            it("正常系") {
                // given:
                val request = CreateVillageRequest.newBuilder()
                    .setName("村名")
                    .setCitizenCount(10)
                    .setWerewolfCount(2)
                    .setFortuneTellerCount(1)
                    .setKnightCount(1)
                    .setPsychicCount(1)
                    .setMadmanCount(1)
                    .setIsInitialActionActive(true)
                    .setPassword("password")
                    .setGameMasterName("GM")
                    .setGameMasterPassword("password")
                    .build()

                val expected = Instancio.create(Village::class.java)

                every {
                    createVillageUseCase.invoke(
                        gameMasterName = "GM",
                        gameMasterPassword = "password",
                        villageName = "村名",
                        villageCitizenCount = 10,
                        villageWerewolfCount = 2,
                        villageFortuneTellerCount = 1,
                        villageKnightCount = 1,
                        villagePsychicCount = 1,
                        villageMadmanCount = 1,
                        villageIsInitialActionActive = true,
                        villagePassword = "password",
                    )
                } returns expected

                val spiedResponseObserver = object : StreamObserver<CreateVillageResponse> {
                    override fun onNext(value: CreateVillageResponse) {
                        value.id shouldBe expected.id.value.toString()
                        value.name shouldBe expected.name
                        value.userNumber shouldBe expected.userNumber
                        value.citizenCount shouldBe expected.citizenCount
                        value.werewolfCount shouldBe expected.werewolfCount
                        value.fortuneTellerCount shouldBe expected.fortuneTellerCount
                        value.knightCount shouldBe expected.knightCount
                        value.psychicCount shouldBe expected.psychicCount
                        value.madmanCount shouldBe expected.madmanCount
                        value.isInitialActionActive shouldBe expected.isInitialActionActive
                        value.gameMasterUserId shouldBe expected.gameMasterUserId.value.toString()
                        value.currentUserNumber shouldBe expected.currentUserNumber
                    }

                    override fun onError(t: Throwable) {
                        throw t
                    }

                    override fun onCompleted() {
                        // do nothing
                    }
                }.let { spyk(it) }

                // when:
                service.createVillage(request, spiedResponseObserver)

                // then:
                verify(exactly = 1) {
                    createVillageUseCase.invoke(
                        gameMasterName = "GM",
                        gameMasterPassword = "password",
                        villageName = "村名",
                        villageCitizenCount = 10,
                        villageWerewolfCount = 2,
                        villageFortuneTellerCount = 1,
                        villageKnightCount = 1,
                        villagePsychicCount = 1,
                        villageMadmanCount = 1,
                        villageIsInitialActionActive = true,
                        villagePassword = "password",
                    )
                    spiedResponseObserver.onCompleted()
                }
            }
            it("usecase 層でエラーが発生") {
                // given:
                val request = CreateVillageRequest.newBuilder()
                    .setName("村名")
                    .setCitizenCount(10)
                    .setWerewolfCount(2)
                    .setFortuneTellerCount(1)
                    .setKnightCount(1)
                    .setPsychicCount(1)
                    .setMadmanCount(1)
                    .setIsInitialActionActive(true)
                    .setPassword("password")
                    .setGameMasterName("GM")
                    .setGameMasterPassword("password")
                    .build()

                every {
                    createVillageUseCase.invoke(
                        gameMasterName = "GM",
                        gameMasterPassword = "password",
                        villageName = "村名",
                        villageCitizenCount = 10,
                        villageWerewolfCount = 2,
                        villageFortuneTellerCount = 1,
                        villageKnightCount = 1,
                        villagePsychicCount = 1,
                        villageMadmanCount = 1,
                        villageIsInitialActionActive = true,
                        villagePassword = "password",
                    )
                } throws Status.UNKNOWN.asRuntimeException()

                val spiedResponseObserver = object : StreamObserver<CreateVillageResponse> {
                    override fun onNext(value: CreateVillageResponse) {
                        // do nothing
                    }

                    override fun onError(t: Throwable) {
                        // do nothing
                    }

                    override fun onCompleted() {
                        // do nothing
                    }
                }.let { spyk(it) }

                // when:
                val e = shouldThrow<StatusRuntimeException> { service.createVillage(request, spiedResponseObserver) }

                // then:
                e.status shouldBe Status.UNKNOWN
                verify(exactly = 1) {
                    createVillageUseCase.invoke(
                        gameMasterName = "GM",
                        gameMasterPassword = "password",
                        villageName = "村名",
                        villageCitizenCount = 10,
                        villageWerewolfCount = 2,
                        villageFortuneTellerCount = 1,
                        villageKnightCount = 1,
                        villagePsychicCount = 1,
                        villageMadmanCount = 1,
                        villageIsInitialActionActive = true,
                        villagePassword = "password",
                    )
                }
                verify(exactly = 0) { spiedResponseObserver.onCompleted() }
            }
        }

        this.describe("listVillages") {
            it("正常系") {
                // given:
                val request = ListVillagesRequest.newBuilder()
                    .build()

                val expectedList = Instancio.createList(Village::class.java)
                every { listVillagesUseCase.invoke() } returns expectedList

                val spiedResponseObserver = object : StreamObserver<ListVillagesResponse> {
                    override fun onNext(value: ListVillagesResponse) {
                        val sortedExpectedList = expectedList.sortedBy { it.id.value.toString() }
                        val actualSortedList = value.villagesList.sortedBy { it.id }
                        actualSortedList
                            .zip(sortedExpectedList)
                            .forEach { (actual, expected) ->
                                actual.id shouldBe expected.id.value.toString()
                                actual.name shouldBe expected.name
                                actual.userNumber shouldBe expected.userNumber
                                actual.citizenCount shouldBe expected.citizenCount
                                actual.werewolfCount shouldBe expected.werewolfCount
                                actual.fortuneTellerCount shouldBe expected.fortuneTellerCount
                                actual.knightCount shouldBe expected.knightCount
                                actual.psychicCount shouldBe expected.psychicCount
                                actual.madmanCount shouldBe expected.madmanCount
                                actual.isInitialActionActive shouldBe expected.isInitialActionActive
                                actual.currentUserNumber shouldBe expected.currentUserNumber
                            }
                    }

                    override fun onError(t: Throwable) {
                        // do nothing
                    }

                    override fun onCompleted() {
                        // do nothing
                    }
                }.let { spyk(it) }

                // when:
                service.listVillages(request, spiedResponseObserver)

                // then:
                verify(exactly = 1) {
                    listVillagesUseCase.invoke()
                    spiedResponseObserver.onCompleted()
                }
            }
            it("usecase 層でエラーが発生") {
                // given:
                val request = ListVillagesRequest.newBuilder()
                    .build()

                every { listVillagesUseCase.invoke() } throws Status.UNKNOWN.asRuntimeException()

                val spiedResponseObserver = object : StreamObserver<ListVillagesResponse> {
                    override fun onNext(value: ListVillagesResponse) {
                        // do nothing
                    }

                    override fun onError(t: Throwable) {
                        // do nothing
                    }

                    override fun onCompleted() {
                        // do nothing
                    }
                }.let { spyk(it) }

                // when:
                val e = shouldThrow<StatusRuntimeException> { service.listVillages(request, spiedResponseObserver) }

                // then:
                e.status shouldBe Status.UNKNOWN
                verify(exactly = 1) { listVillagesUseCase.invoke() }
                verify(exactly = 0) { spiedResponseObserver.onCompleted() }
            }
        }
    }
}

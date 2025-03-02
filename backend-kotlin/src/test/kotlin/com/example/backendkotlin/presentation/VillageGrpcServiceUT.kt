package com.example.backendkotlin.presentation

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.WerewolfErrorCode
import com.example.backendkotlin.domain.WerewolfException
import com.example.backendkotlin.generated.grpc.CreateVillageRequest
import com.example.backendkotlin.generated.grpc.CreateVillageResponse
import com.example.backendkotlin.generated.grpc.EnterVillageRequest
import com.example.backendkotlin.generated.grpc.EnterVillageResponse
import com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest
import com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse
import com.example.backendkotlin.generated.grpc.ListVillagesRequest
import com.example.backendkotlin.generated.grpc.ListVillagesResponse
import com.example.backendkotlin.usecase.CreateVillageUseCase
import com.example.backendkotlin.usecase.EnterVillageUseCase
import com.example.backendkotlin.usecase.GetCurrentVillageUsersUseCase
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
    @MockkBean
    private val getCurrentVillageUsersUseCase: GetCurrentVillageUsersUseCase,
    @MockkBean
    private val enterVillageUseCase: EnterVillageUseCase,
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
            enterVillageUseCase,
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
                    .setIsRecruitedOnly(true)
                    .build()

                val expectedList = Instancio.createList(Village::class.java)
                every { listVillagesUseCase.invoke(request.isRecruitedOnly) } returns expectedList

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
                    listVillagesUseCase.invoke(request.isRecruitedOnly)
                    spiedResponseObserver.onCompleted()
                }
            }
            it("usecase 層でエラーが発生") {
                // given:
                val request = ListVillagesRequest.newBuilder()
                    .setIsRecruitedOnly(false)
                    .build()

                every { listVillagesUseCase.invoke(request.isRecruitedOnly) } throws Status.UNKNOWN.asRuntimeException()

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
                verify(exactly = 1) { listVillagesUseCase.invoke(request.isRecruitedOnly) }
                verify(exactly = 0) { spiedResponseObserver.onCompleted() }
            }
        }

        this.describe("GetCurrentVillageUsers") {
            it("正常系") {
                // given
                val request = GetCurrentVillageUsersRequest.newBuilder()
                    .setVillageId("1")
                    .setVillagePassword("password")
                    .setUserId("1")
                    .setUserPassword("password")
                    .build()

                val expectedVillage = Instancio.create(Village::class.java)
                val users = listOf(Instancio.create(User::class.java))
                val expectedUserNames = users.map { it.name }

                // and
                every {
                    getCurrentVillageUsersUseCase.invoke(
                        villageIdString = "1",
                        villagePassword = "password",
                        userIdString = "1",
                        userIdPassword = "password",
                    )
                } returns Pair(expectedVillage, users)

                val spiedResponseObserver = object : StreamObserver<GetCurrentVillageUsersResponse> {
                    override fun onNext(value: GetCurrentVillageUsersResponse) {
                        value.villageId shouldBe expectedVillage.id.value.toString()

                        val sortedExpectedUsers = expectedUserNames.sortedBy { it }
                        val sortedActualUsers = value.currentUsersList.sortedBy { it.userName }
                        sortedActualUsers
                            .zip(sortedExpectedUsers)
                            .forEach { (actual, expected) ->
                                actual.userName shouldBe expected
                            }
                    }

                    override fun onError(t: Throwable) {
                        // do nothing
                    }

                    override fun onCompleted() {
                        // do nothing
                    }
                }.let { spyk(it) }

                // when
                service.getCurrentVillageUsers(request, spiedResponseObserver)

                // then
                verify(exactly = 1) {
                    getCurrentVillageUsersUseCase.invoke(
                        villageIdString = "1",
                        villagePassword = "password",
                        userIdString = "1",
                        userIdPassword = "password",
                    )
                }
            }
        }

        this.describe("enterVillage") {
            context("正常系") {
                it("村にログインできる") {
                    // given:
                    val request = EnterVillageRequest.newBuilder()
                        .setVillageId("村ID")
                        .setVillagePassword("password")
                        .setUserName("ユーザー名")
                        .setUserPassword("password")
                        .build()

                    val expected = Pair(UserId.generate(), VillageId.generate())
                    every {
                        enterVillageUseCase.invoke(
                            villageIdString = "村ID",
                            villagePassword = "password",
                            userName = "ユーザー名",
                            userPassword = "password",
                        )
                    } returns expected

                    val spiedResponseObserver = object : StreamObserver<EnterVillageResponse> {
                        override fun onNext(value: EnterVillageResponse) {
                            value.userId shouldBe expected.first.value.toString()
                            value.villageId shouldBe expected.second.value.toString()
                        }

                        override fun onError(t: Throwable) {
                            // do nothing
                        }

                        override fun onCompleted() {
                            // do nothing
                        }
                    }.let { spyk(it) }

                    // when:
                    service.enterVillage(request, spiedResponseObserver)

                    // then:
                    verify(exactly = 1) {
                        enterVillageUseCase.invoke(
                            villageIdString = "村ID",
                            villagePassword = "password",
                            userName = "ユーザー名",
                            userPassword = "password",
                        )
                        spiedResponseObserver.onCompleted()
                    }
                }
            }
            context("異常系") {
                it("村IDが存在していなかったらNOT_FOUNDを返す") {
                    // given:
                    val request = EnterVillageRequest.newBuilder()
                        .setVillageId("村ID")
                        .setVillagePassword("password")
                        .setUserName("ユーザー名")
                        .setUserPassword("password")
                        .build()

                    every {
                        enterVillageUseCase.invoke(
                            villageIdString = "村ID",
                            villagePassword = "password",
                            userName = "ユーザー名",
                            userPassword = "password",
                        )
                    } throws WerewolfException(WerewolfErrorCode.RESOURCE_NOT_FOUND, "村が存在しません")

                    val spiedResponseObserver = object : StreamObserver<EnterVillageResponse> {
                        override fun onNext(value: EnterVillageResponse) {
                            // do nothing
                        }

                        override fun onError(t: Throwable) {
                            t.message shouldBe "NOT_FOUND: The village does not exist"
                        }

                        override fun onCompleted() {
                            // do nothing
                        }
                    }.let { spyk(it) }

                    // when:
                    service.enterVillage(request, spiedResponseObserver)

                    // then:
                    verify(exactly = 1) {
                        enterVillageUseCase.invoke(
                            villageIdString = "村ID",
                            villagePassword = "password",
                            userName = "ユーザー名",
                            userPassword = "password",
                        )
                    }
                    verify(exactly = 0) { spiedResponseObserver.onCompleted() }
                }
                it("村のパスワードが違う場合INVALID_ARGUMENTを返す") {
                    // given:
                    val request = EnterVillageRequest.newBuilder()
                        .setVillageId("村ID")
                        .setVillagePassword("password")
                        .setUserName("ユーザー名")
                        .setUserPassword("password")
                        .build()

                    every {
                        enterVillageUseCase.invoke(
                            villageIdString = "村ID",
                            villagePassword = "password",
                            userName = "ユーザー名",
                            userPassword = "password",
                        )
                    } throws WerewolfException(WerewolfErrorCode.VILLAGE_PASSWORD_IS_WRONG, "村のパスワードが違います")

                    val spiedResponseObserver = object : StreamObserver<EnterVillageResponse> {
                        override fun onNext(value: EnterVillageResponse) {
                            // do nothing
                        }

                        override fun onError(t: Throwable) {
                            t.message shouldBe "INVALID_ARGUMENT: The village password is wrong"
                        }

                        override fun onCompleted() {
                            // do nothing
                        }
                    }.let { spyk(it) }

                    // when:
                    service.enterVillage(request, spiedResponseObserver)

                    // then:
                    verify(exactly = 1) {
                        enterVillageUseCase.invoke(
                            villageIdString = "村ID",
                            villagePassword = "password",
                            userName = "ユーザー名",
                            userPassword = "password",
                        )
                    }
                    verify(exactly = 0) { spiedResponseObserver.onCompleted() }
                }
            }
        }
    }
}

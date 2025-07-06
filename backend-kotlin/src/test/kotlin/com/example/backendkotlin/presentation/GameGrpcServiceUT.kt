package com.example.backendkotlin.presentation

import com.example.backendkotlin.domain.ActionLog
import com.example.backendkotlin.domain.Actor
import com.example.backendkotlin.domain.ActorTeam
import com.example.backendkotlin.domain.Game
import com.example.backendkotlin.domain.GameId
import com.example.backendkotlin.domain.GameTerm
import com.example.backendkotlin.generated.grpc.ExecSelfActionRequest
import com.example.backendkotlin.generated.grpc.ExecSelfActionResponse
import com.example.backendkotlin.generated.grpc.GetActorRequest
import com.example.backendkotlin.generated.grpc.GetActorResponse
import com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest
import com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse
import com.example.backendkotlin.generated.grpc.GetGameResultRequest
import com.example.backendkotlin.generated.grpc.GetGameResultResponse
import com.example.backendkotlin.generated.grpc.GetGameStatusRequest
import com.example.backendkotlin.generated.grpc.GetGameStatusResponse
import com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest
import com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse
import com.example.backendkotlin.infrastructure.db.table.GameStatusTable.day
import com.example.backendkotlin.usecase.ExecSelfActionUseCase
import com.example.backendkotlin.usecase.GetActorUseCase
import com.example.backendkotlin.usecase.GetDeadPlayersLogUseCase
import com.example.backendkotlin.usecase.GetGameResultUseCase
import com.example.backendkotlin.usecase.GetGameStatusUseCase
import com.example.backendkotlin.usecase.GetSelfActionLogUseCase
import com.example.backendkotlin.util.KSelect
import com.example.backendkotlin.utils.SleepUtil
import com.ninjasquad.springmockk.MockkBean
import io.grpc.stub.StreamObserver
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.just
import io.mockk.mockkObject
import io.mockk.runs
import io.mockk.spyk
import io.mockk.unmockkObject
import io.mockk.verify
import org.instancio.Instancio
import kotlin.jvm.java

class GameGrpcServiceUT(
    @MockkBean
    private val getActorUseCase: GetActorUseCase,
    @MockkBean
    private val getGameStatusUseCase: GetGameStatusUseCase,
    @MockkBean
    private val getDeadPlayersLogUseCase: GetDeadPlayersLogUseCase,
    @MockkBean
    private val getSelfActionLogUseCase: GetSelfActionLogUseCase,
    @MockkBean
    private val execSelfActionUseCase: ExecSelfActionUseCase,
    @MockkBean
    private val getGameResultUseCase: GetGameResultUseCase,
) : DescribeSpec() {
    @InjectMockKs
    private lateinit var service: GameGrpcService

    override suspend fun beforeSpec(spec: Spec) {
        mockkObject(SleepUtil)
        every { SleepUtil.threadSleep(more(0L)) } just runs
    }

    override suspend fun afterSpec(spec: Spec) {
        unmockkObject(SleepUtil)
    }

    override suspend fun beforeTest(testCase: TestCase) {
        MockKAnnotations.init(this)
        clearAllMocks()
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        confirmVerified(
            getActorUseCase,
            getGameStatusUseCase,
            getDeadPlayersLogUseCase,
            getSelfActionLogUseCase,
            execSelfActionUseCase,
            getGameResultUseCase,
        )
    }

    init {
        this.describe("getActor") {
            context("正常系") {
                it("プレイヤーの役職を取得できる") {
                    // given:
                    val request = GetActorRequest.newBuilder()
                        .setGameId("gameId")
                        .setUserId("userId")
                        .setUserPassword("password")
                        .build()

                    val expectedActor = Instancio.create(Actor::class.java)
                    every {
                        getActorUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                        )
                    } returns expectedActor

                    val spiedResponseObserver = object : StreamObserver<GetActorResponse> {
                        override fun onNext(value: GetActorResponse) {
                            value.actor shouldBe expectedActor.id
                        }

                        override fun onError(t: Throwable) {
                            // do nothing
                        }

                        override fun onCompleted() {
                            // do nothing
                        }
                    }.let { spyk(it) }

                    // when:
                    service.getActor(request, spiedResponseObserver)

                    // then:
                    verify(exactly = 1) {
                        getActorUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                        )
                        spiedResponseObserver.onCompleted()
                    }
                }
            }
        }

        this.describe("getGameStatus") {
            context("正常系") {
                it("実行1回目はisPlayingがtrueで継続し、2回目でfalseになり終了する") {
                    // given
                    val gameId = Instancio.create(GameId::class.java)
                    val request = GetGameStatusRequest.newBuilder()
                        .setGameId(gameId.value.toString())
                        .setUserId("userId")
                        .setUserPassword("password")
                        .build()

                    val expectedGameStatus1 = Instancio.of(Game::class.java)
                        .set(KSelect.field(Game::id), gameId)
                        .set(KSelect.field(Game::day), 1)
                        .set(KSelect.field(Game::isPlaying), true)
                        .create()
                    val expectedGameStatus2 = Instancio.of(Game::class.java)
                        .set(KSelect.field(Game::id), gameId)
                        .set(KSelect.field(Game::day), 1)
                        .set(KSelect.field(Game::isPlaying), false)
                        .create()

                    val expectedResults = listOf(
                        expectedGameStatus1,
                        expectedGameStatus2,
                    )

                    // and
                    every {
                        getGameStatusUseCase.invoke(
                            gameIdString = gameId.value.toString(),
                            userIdString = "userId",
                            userPassword = "password",
                        )
                    } returnsMany expectedResults

                    val spiedResponseObserver = object : StreamObserver<GetGameStatusResponse> {
                        override fun onNext(value: GetGameStatusResponse) {
                            value.day shouldBe expectedResults.first().day
                        }

                        override fun onError(t: Throwable) {
                            // do nothing
                        }

                        override fun onCompleted() {
                            // do nothing
                        }
                    }.let { spyk(it) }

                    // when
                    service.getGameStatus(request, spiedResponseObserver)

                    // then
                    verify(exactly = 2) {
                        getGameStatusUseCase.invoke(
                            gameIdString = gameId.value.toString(),
                            userIdString = "userId",
                            userPassword = "password",
                        )
                    }
                    verify(exactly = 1) {
                        spiedResponseObserver.onCompleted()
                        SleepUtil.threadSleep(3000)
                    }
                }
            }
        }

        this.describe("getDeadPlayersLog") {
            context("正常系") {
                it("死亡したプレイヤーのログを取得できる") {
                    // given:
                    val request = GetDeadPlayersLogRequest.newBuilder()
                        .setGameId("gameId")
                        .setUserId("userId")
                        .setUserPassword("password")
                        .build()

                    val expectedLogs = listOf(
                        Triple(1, GameTerm.DAY, "Taro"),
                        Triple(1, GameTerm.NIGHT, "Hanako"),
                    )
                    every {
                        getDeadPlayersLogUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                        )
                    } returns expectedLogs

                    val spiedResponseObserver = object : StreamObserver<GetDeadPlayersLogResponse> {
                        override fun onNext(value: GetDeadPlayersLogResponse) {
                            value.logsList.size shouldBe expectedLogs.size
                            value.logsList.forEachIndexed { index, log ->
                                log.day shouldBe expectedLogs[index].first
                                log.term shouldBe expectedLogs[index].second.ordinal
                                log.targetPlayer shouldBe expectedLogs[index].third
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
                    service.getDeadPlayersLog(request, spiedResponseObserver)

                    // then:
                    verify(exactly = 1) {
                        getDeadPlayersLogUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                        )
                        spiedResponseObserver.onCompleted()
                    }
                }
            }
        }

        this.describe("getSelfActionLog") {
            context("正常系") {
                it("自分のアクションログを取得できる") {
                    // given:
                    val request = GetSelfActionLogRequest.newBuilder()
                        .setGameId("gameId")
                        .setUserId("userId")
                        .setUserPassword("password")
                        .build()

                    val expectedLogs = listOf(
                        Instancio.create(ActionLog::class.java),
                        Instancio.create(ActionLog::class.java),
                    )
                    every {
                        getSelfActionLogUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                        )
                    } returns expectedLogs

                    val spiedResponseObserver = object : StreamObserver<GetSelfActionLogResponse> {
                        override fun onNext(value: GetSelfActionLogResponse) {
                            value.logsList.size shouldBe expectedLogs.size
                            value.logsList.forEachIndexed { index, log ->
                                log.day shouldBe expectedLogs[index].day
                                log.term shouldBe expectedLogs[index].term.ordinal
                                log.targetUserName shouldBe expectedLogs[index].targetPlayer.user.name
                                log.result shouldBe (expectedLogs[index].result?.ordinal != 0)
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
                    service.getSelfActionLog(request, spiedResponseObserver)

                    // then:
                    verify(exactly = 1) {
                        getSelfActionLogUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                        )
                        spiedResponseObserver.onCompleted()
                    }
                }
            }
        }

        this.describe("execSelfAction") {
            context("正常系") {
                it("アクションを実行できる") {
                    // given:
                    val request = ExecSelfActionRequest.newBuilder()
                        .setGameId("gameId")
                        .setUserId("userId")
                        .setUserPassword("password")
                        .setTargetPlayerId("targetPlayerId")
                        .build()

                    val expectedActionResult = Instancio.create(ActorTeam::class.java)
                    every {
                        execSelfActionUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                            targetPlayerIdString = request.targetPlayerId,
                        )
                    } returns expectedActionResult

                    val spiedResponseObserver = object : StreamObserver<ExecSelfActionResponse> {
                        override fun onNext(value: ExecSelfActionResponse) {
                            val expectedResult = when (expectedActionResult.ordinal) {
                                0 -> 1 // WEREWOLF -> 1
                                else -> 0 // CITIZEN -> 0
                            }
                            value.result shouldBe expectedResult
                        }

                        override fun onError(t: Throwable) {
                            // do nothing
                        }

                        override fun onCompleted() {
                            // do nothing
                        }
                    }.let { spyk(it) }

                    // when:
                    service.execSelfAction(request, spiedResponseObserver)

                    // then:
                    verify(exactly = 1) {
                        execSelfActionUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                            targetPlayerIdString = request.targetPlayerId,
                        )
                        spiedResponseObserver.onCompleted()
                    }
                }
            }
        }

        this.describe("getGameResult") {
            context("正常系") {
                it("ゲームの結果を取得できる") {
                    // given:
                    val request = GetGameResultRequest.newBuilder()
                        .setGameId("gameId")
                        .setUserId("userId")
                        .setUserPassword("password")
                        .build()

                    val expectedGameResult = Instancio.create(ActorTeam::class.java)
                    every {
                        getGameResultUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                        )
                    } returns expectedGameResult

                    val spiedResponseObserver = object : StreamObserver<GetGameResultResponse> {
                        override fun onNext(value: GetGameResultResponse) {
                            value.result shouldBe expectedGameResult.ordinal
                        }

                        override fun onError(t: Throwable) {
                            // do nothing
                        }

                        override fun onCompleted() {
                            // do nothing
                        }
                    }.let { spyk(it) }

                    // when:
                    service.getGameResult(request, spiedResponseObserver)

                    // then:
                    verify(exactly = 1) {
                        getGameResultUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                        )
                        spiedResponseObserver.onCompleted()
                    }
                }
                it("ゲームの結果がnullの場合、空のレスポンスを返す") {
                    // given:
                    val request = GetGameResultRequest.newBuilder()
                        .setGameId("gameId")
                        .setUserId("userId")
                        .setUserPassword("password")
                        .build()

                    every {
                        getGameResultUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                        )
                    } returns null

                    val spiedResponseObserver = object : StreamObserver<GetGameResultResponse> {
                        override fun onNext(value: GetGameResultResponse) {
                            value.result shouldBe 0 // デフォルト値
                        }

                        override fun onError(t: Throwable) {
                            // do nothing
                        }

                        override fun onCompleted() {
                            // do nothing
                        }
                    }.let { spyk(it) }

                    // when:
                    service.getGameResult(request, spiedResponseObserver)

                    // then:
                    verify(exactly = 1) {
                        getGameResultUseCase.invoke(
                            gameIdString = request.gameId,
                            userIdString = request.userId,
                            userPassword = request.userPassword,
                        )
                        spiedResponseObserver.onCompleted()
                    }
                }
            }
        }
    }
}

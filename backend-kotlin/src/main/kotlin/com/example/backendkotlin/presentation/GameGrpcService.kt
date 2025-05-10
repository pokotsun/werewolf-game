package com.example.backendkotlin.presentation

import com.example.backendkotlin.generated.grpc.DeadPlayerLog
import com.example.backendkotlin.generated.grpc.ExecSelfActionRequest
import com.example.backendkotlin.generated.grpc.ExecSelfActionResponse
import com.example.backendkotlin.generated.grpc.GameServiceGrpc
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
import com.example.backendkotlin.generated.grpc.PlayerDeadAlive
import com.example.backendkotlin.generated.grpc.SelfActionLog
import com.example.backendkotlin.usecase.ExecSelfActionUseCase
import com.example.backendkotlin.usecase.GetActorUseCase
import com.example.backendkotlin.usecase.GetDeadPlayersLogUseCase
import com.example.backendkotlin.usecase.GetGameResultUseCase
import com.example.backendkotlin.usecase.GetGameStatusUseCase
import com.example.backendkotlin.usecase.GetSelfActionLogUseCase
import com.example.backendkotlin.utils.SleepUtil
import com.github.michaelbull.result.fold
import com.github.michaelbull.result.mapBoth
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import kotlin.collections.map

/**
 * ゲームに関するgRPCサービス
 */
@GrpcService
class GameGrpcService(
    private val getActorUseCase: GetActorUseCase,
    private val getGameStatusUseCase: GetGameStatusUseCase,
    private val getDeadPlayersLogUseCase: GetDeadPlayersLogUseCase,
    private val getSelfActionLogUseCase: GetSelfActionLogUseCase,
    private val execSelfActionUseCase: ExecSelfActionUseCase,
    private val getGameResultUseCase: GetGameResultUseCase,
) : GameServiceGrpc.GameServiceImplBase(), GrpcServiceExceptionHandler {

    /**
     * プレイヤーの役職を取得する
     *
     * @param request 役職取得リクエスト
     * @param responseObserver レスポンス
     * @return プレイヤーの役職
     */
    override fun getActor(
        request: GetActorRequest,
        responseObserver: StreamObserver<GetActorResponse>,
    ) {
        val result = handleException {
            getActorUseCase.invoke(
                gameIdString = request.gameId,
                userIdString = request.userId,
                userPassword = request.userPassword,
            )
        }
        result.fold(
            success = { actor ->
                val response = GetActorResponse.newBuilder()
                    .setActor(actor.id)
                    .build()
                responseObserver.let { r ->
                    r.onNext(response)
                    r.onCompleted()
                }
            },
            failure = { e ->
                responseObserver.onError(e)
            },
        )
    }

    /**
     * ゲームの進行状況を取得する
     *
     * @param request ゲーム状況取得リクエスト
     * @param responseObserver レスポンス
     * @return ゲームの進行状況（フェーズ、日数、プレイヤーの状態など）
     */
    override fun getGameStatus(
        request: GetGameStatusRequest,
        responseObserver: StreamObserver<GetGameStatusResponse>,
    ) {
        // ServerStreamingの実装。ゲームがプレイ中の間はレスポンスし続ける。
        do {
            val result = handleException {
                getGameStatusUseCase.invoke(
                    gameIdString = request.gameId,
                    userIdString = request.userId,
                    userPassword = request.userPassword,
                )
            }
            val shouldContinue = result.mapBoth(
                success = { gameStatus ->
                    val playersDeadAlive = listOf(
                        PlayerDeadAlive.newBuilder()
                            .setPlayerName("player1")
                            .setIsDead(false)
                            .build(),
                    )
                    val response = GetGameStatusResponse.newBuilder()
                        .setDay(gameStatus.day)
                        .setTerm(gameStatus.term.ordinal)
                        .setDidAction(true)
                        .setWaitingActionCount(0)
                        .setIsPlaying(true)
                        .addAllPlayerDeadAlive(
                            playersDeadAlive,
                        )
                        .build()
                    responseObserver.onNext(response)

                    // ゲームが進行中の場合は3秒待機する.
                    // それ以外の場合は処理を終了する
                    if (gameStatus.isPlaying) {
                        SleepUtil.threadSleep(3000)
                    } else {
                        responseObserver.onCompleted()
                    }

                    // ゲームが進行中の場合はtrueを返し処理を続行する
                    gameStatus.isPlaying
                },
                failure = { e ->
                    // エラーが発生した場合は、エラーメッセージをレスポンスとして返す
                    responseObserver.onError(e)

                    // 処理を終了する
                    false
                },
            )
        } while (shouldContinue)
    }

    /**
     * 死亡したプレイヤーのログを取得する
     *
     * @param request 死亡プレイヤーログ取得リクエスト
     * @param responseObserver レスポンス
     * @return 死亡したプレイヤーのログ一覧
     */
    override fun getDeadPlayersLog(
        request: GetDeadPlayersLogRequest,
        responseObserver: StreamObserver<GetDeadPlayersLogResponse>,
    ) {
        val result = handleException {
            getDeadPlayersLogUseCase.invoke(
                gameIdString = request.gameId,
                userIdString = request.userId,
                userPassword = request.userPassword,
            )
        }
        result.fold(
            success = { logs ->
                val response = GetDeadPlayersLogResponse.newBuilder()
                    .addAllLogs(
                        logs.map { log ->
                            DeadPlayerLog.newBuilder()
                                .setDay(log.first)
                                .setTerm(log.second.ordinal)
                                .setTargetPlayer(log.third)
                                .build()
                        },
                    )
                    .build()
                responseObserver.let { r ->
                    r.onNext(response)
                    r.onCompleted()
                }
            },
            failure = { e ->
                responseObserver.onError(e)
            },
        )
    }

    /**
     * 自分のアクションログを取得する
     *
     * @param request アクションログ取得リクエスト
     * @param responseObserver レスポンス
     * @return 自分のアクションログ一覧
     */
    override fun getSelfActionLog(
        request: GetSelfActionLogRequest,
        responseObserver: StreamObserver<GetSelfActionLogResponse>,
    ) {
        val result = handleException {
            getSelfActionLogUseCase.invoke(
                gameIdString = request.gameId,
                userIdString = request.userId,
                userPassword = request.userPassword,
            )
        }
        result.fold(
            success = { logs ->
                val response = GetSelfActionLogResponse.newBuilder()
                    .addAllLogs(
                        logs.map { log ->
                            val result = when (log.result?.ordinal) {
                                0 -> false
                                else -> true
                            }
                            SelfActionLog.newBuilder()
                                .setDay(log.day)
                                .setTerm(log.term.ordinal)
                                .setTargetUserName(log.targetPlayer.user.name)
                                .setResult(result)
                                .build()
                        },
                    )
                    .build()
                responseObserver.let { r ->
                    r.onNext(response)
                    r.onCompleted()
                }
            },
            failure = { e ->
                responseObserver.onError(e)
            },
        )
    }

    /**
     * アクションを実行する
     *
     * @param request アクション実行リクエスト
     * @param responseObserver レスポンス
     * @return アクションの実行結果
     */
    override fun execSelfAction(
        request: ExecSelfActionRequest,
        responseObserver: StreamObserver<ExecSelfActionResponse>,
    ) {
        val result = handleException {
            execSelfActionUseCase.invoke(
                gameIdString = request.gameId,
                userIdString = request.userId,
                userPassword = request.userPassword,
                targetPlayerIdString = request.targetPlayerId,
            )
        }
        result.fold(
            success = { actionResult ->
                val result = when (actionResult?.ordinal) {
                    0 -> 1
                    else -> 0
                }
                val response = ExecSelfActionResponse.newBuilder()
                    .setResult(result)
                    .build()
                responseObserver.let { r ->
                    r.onNext(response)
                    r.onCompleted()
                }
            },
            failure = { e ->
                responseObserver.onError(e)
            },
        )
    }

    /**
     * ゲームの結果を取得する
     *
     * @param request ゲーム結果取得リクエスト
     * @param responseObserver レスポンス
     * @return ゲームの結果（0: 市民陣営の勝利、1: 人狼陣営の勝利）
     */
    override fun getGameResult(
        request: GetGameResultRequest,
        responseObserver: StreamObserver<GetGameResultResponse>,
    ) {
        val result = handleException {
            getGameResultUseCase.invoke(
                gameIdString = request.gameId,
                userIdString = request.userId,
                userPassword = request.userPassword,
            )
        }
        result.fold(
            success = { gameResult ->
                val response = if (gameResult == null) {
                    GetGameResultResponse.newBuilder().build()
                } else {
                    GetGameResultResponse.newBuilder()
                        .setResult(gameResult.ordinal)
                        .build()
                }
                responseObserver.let { r ->
                    r.onNext(response)
                    r.onCompleted()
                }
            },
            failure = { e ->
                responseObserver.onError(e)
            },
        )
    }
} 

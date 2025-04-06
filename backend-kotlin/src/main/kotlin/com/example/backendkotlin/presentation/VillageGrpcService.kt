package com.example.backendkotlin.presentation

import com.example.backendkotlin.generated.grpc.CreateVillageRequest
import com.example.backendkotlin.generated.grpc.CreateVillageResponse
import com.example.backendkotlin.generated.grpc.CurrentUserResponse
import com.example.backendkotlin.generated.grpc.EnterVillageRequest
import com.example.backendkotlin.generated.grpc.EnterVillageResponse
import com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest
import com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse
import com.example.backendkotlin.generated.grpc.GetVillageRequest
import com.example.backendkotlin.generated.grpc.GetVillageResponse
import com.example.backendkotlin.generated.grpc.ListVillagesRequest
import com.example.backendkotlin.generated.grpc.ListVillagesResponse
import com.example.backendkotlin.generated.grpc.VillageResponse
import com.example.backendkotlin.generated.grpc.VillageServiceGrpc
import com.example.backendkotlin.infrastructure.db.table.VillageTable.isRecruited
import com.example.backendkotlin.usecase.CreateVillageUseCase
import com.example.backendkotlin.usecase.EnterVillageUseCase
import com.example.backendkotlin.usecase.GetCurrentVillageUsersUseCase
import com.example.backendkotlin.usecase.GetVillageUseCase
import com.example.backendkotlin.usecase.ListVillagesUseCase
import com.example.backendkotlin.utils.SleepUtil
import com.github.michaelbull.result.fold
import com.github.michaelbull.result.mapBoth
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

/**
 * 村に関するgRPCサービス
 */
@GrpcService
class VillageGrpcService(
    private val listVillagesUseCase: ListVillagesUseCase,
    private val createVillageUseCase: CreateVillageUseCase,
    private val getCurrentVillageUsersUseCase: GetCurrentVillageUsersUseCase,
    private val enterVillageUseCase: EnterVillageUseCase,
    private val getVillageUseCase: GetVillageUseCase,
) : VillageServiceGrpc.VillageServiceImplBase(), GrpcServiceExceptionHandler {
    /**
     * 村を作成する
     *
     * @param request 村作成リクエスト
     * @param responseObserver レスポンス
     *
     * @return 村
     */
    override fun createVillage(
        request: CreateVillageRequest,
        responseObserver: StreamObserver<CreateVillageResponse>,
    ) {
        // 村を作成
        val createdVillage = createVillageUseCase.invoke(
            gameMasterName = request.gameMasterName,
            gameMasterPassword = request.gameMasterPassword,
            villageName = request.name,
            villageCitizenCount = request.citizenCount,
            villageWerewolfCount = request.werewolfCount,
            villageFortuneTellerCount = request.fortuneTellerCount,
            villageKnightCount = request.knightCount,
            villagePsychicCount = request.psychicCount,
            villageMadmanCount = request.madmanCount,
            villageIsInitialActionActive = request.isInitialActionActive,
            villagePassword = request.password,
        )
        // レスポンスを作成
        val createVillageResponse = createdVillage.let { res ->
            CreateVillageResponse.newBuilder()
                .setId(res.id.value.toString())
                .setName(res.name)
                .setUserNumber(res.userNumber)
                .setCitizenCount(res.citizenCount)
                .setWerewolfCount(res.werewolfCount)
                .setFortuneTellerCount(res.fortuneTellerCount)
                .setKnightCount(res.knightCount)
                .setPsychicCount(res.psychicCount)
                .setMadmanCount(res.madmanCount)
                .setIsInitialActionActive(res.isInitialActionActive)
                .setGameMasterUserId(res.gameMasterUserId.value.toString())
                .setCurrentUserNumber(res.currentUserNumber)
                .build()
        }
        responseObserver.let { r ->
            r.onNext(createVillageResponse)
            r.onCompleted()
        }
    }

    /**
     * 村の一覧を取得する
     *
     * @param request 村一覧取得リクエスト
     * @param responseObserver レスポンス
     *
     * @return 村一覧
     */
    override fun listVillages(request: ListVillagesRequest, responseObserver: StreamObserver<ListVillagesResponse>) {
        // 村一覧を取得
        val villages = listVillagesUseCase.invoke(request.isRecruitedOnly)
        // レスポンスを作成
        val villageResponseList = villages.map { res ->
            VillageResponse.newBuilder()
                .setId(res.id.value.toString())
                .setName(res.name)
                .setUserNumber(res.userNumber)
                .setCitizenCount(res.citizenCount)
                .setWerewolfCount(res.werewolfCount)
                .setFortuneTellerCount(res.fortuneTellerCount)
                .setKnightCount(res.knightCount)
                .setPsychicCount(res.psychicCount)
                .setMadmanCount(res.madmanCount)
                .setIsInitialActionActive(res.isInitialActionActive)
                .setCurrentUserNumber(res.currentUserNumber)
                .build()
        }
        val listVillagesResponse = ListVillagesResponse.newBuilder().addAllVillages(villageResponseList).build()
        responseObserver.let { r ->
            r.onNext(listVillagesResponse)
            r.onCompleted()
        }
    }

    /**
     * 特定の村を取得する
     *
     * @param request 村取得リクエスト
     * @param responseObserver レスポンス
     *
     * @return 村
     */
    override fun getVillage(request: GetVillageRequest, responseObserver: StreamObserver<GetVillageResponse>) {
        val result = handleException {
            // 村を取得
            getVillageUseCase.invoke(request.villageId)
        }
        result.fold(
            success = { village ->
                // レスポンスを作成
                val getVillageResponse = GetVillageResponse.newBuilder()
                    .setId(village.id.value.toString())
                    .setName(village.name)
                    .setUserNumber(village.userNumber)
                    .setCitizenCount(village.citizenCount)
                    .setWerewolfCount(village.werewolfCount)
                    .setFortuneTellerCount(village.fortuneTellerCount)
                    .setKnightCount(village.knightCount)
                    .setPsychicCount(village.psychicCount)
                    .setMadmanCount(village.madmanCount)
                    .setIsInitialActionActive(village.isInitialActionActive)
                    .setCurrentUserNumber(village.currentUserNumber)
                    .build()
                responseObserver.let { r ->
                    r.onNext(getVillageResponse)
                    r.onCompleted()
                }
            },
            failure = { e ->
                // エラーが発生した場合はエラーレスポンスを返す
                responseObserver.onError(e)
            },
        )
    }

    /**
     * ユーザーが村に参加する
     *
     * @param request 村参加リクエスト
     * @param responseObserver レスポンス
     *
     * @return 村、ユーザー情報
     */
    override fun enterVillage(request: EnterVillageRequest, responseObserver: StreamObserver<EnterVillageResponse>) {
        val result = handleException {
            // 村に参加
            enterVillageUseCase.invoke(
                villageIdString = request.villageId,
                villagePassword = request.villagePassword,
                userName = request.userName,
                userPassword = request.userPassword,
            )
        }
        result.fold(
            success = { (userId, villageId) ->
                // レスポンスを作成
                val enterVillageResponse = EnterVillageResponse.newBuilder()
                    .setUserId(userId.value.toString())
                    .setVillageId(villageId.value.toString())
                    .build()
                responseObserver.let { r ->
                    r.onNext(enterVillageResponse)
                    r.onCompleted()
                }
            },
            failure = { e ->
                // エラーが発生した場合はエラーレスポンスを返す
                responseObserver.onError(e)
            },
        )
    }

    /**
     * 村のユーザー一覧を取得する
     */
    override fun getCurrentVillageUsers(
        request: GetCurrentVillageUsersRequest,
        responseObserver: StreamObserver<GetCurrentVillageUsersResponse>,
    ) {
        // SeverStreamingの実装。村が募集中の間はレスポンスし続ける。
        do {
            val result = handleException {
                getCurrentVillageUsersUseCase.invoke(
                    request.villageId,
                    request.villagePassword,
                    request.userId,
                    request.userPassword,
                )
            }
            val shouldContinue = result.mapBoth(
                success = { (village, currentUsers) ->
                    // レスポンスを作成
                    val currentUsersResponseList = currentUsers.map { user ->
                        CurrentUserResponse.newBuilder()
                            .setUserName(user.name)
                            .build()
                    }
                    val getCurrentVillageUsersResponse = GetCurrentVillageUsersResponse.newBuilder()
                        .setVillageId(village.id.value.toString())
                        .addAllCurrentUsers(currentUsersResponseList)
                        .build()

                    // レスポンスを返す
                    responseObserver.onNext(getCurrentVillageUsersResponse)
                    if (village.isRecruited) {
                        SleepUtil.threadSleep(3000)
                    } else {
                        // 村が募集中でない場合は処理を終了する
                        responseObserver.onCompleted()
                    }

                    // 村が募集中の場合は処理を続ける
                    village.isRecruited
                },
                failure = { e ->
                    // エラーが発生した場合はエラーレスポンスを返す
                    responseObserver.onError(e)

                    // 処理を終了する
                    false
                },
            )
        } while (shouldContinue)
    }
}

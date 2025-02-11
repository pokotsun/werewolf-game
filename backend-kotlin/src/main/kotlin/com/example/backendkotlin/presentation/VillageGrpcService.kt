package com.example.backendkotlin.presentation

import com.example.backendkotlin.generated.grpc.CreateVillageRequest
import com.example.backendkotlin.generated.grpc.CreateVillageResponse
import com.example.backendkotlin.generated.grpc.ListVillagesRequest
import com.example.backendkotlin.generated.grpc.ListVillagesResponse
import com.example.backendkotlin.generated.grpc.VillageResponse
import com.example.backendkotlin.generated.grpc.VillageServiceGrpc
import com.example.backendkotlin.usecase.CreateVillageUseCase
import com.example.backendkotlin.usecase.ListVillagesUseCase
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

/**
 * 村に関するgRPCサービス
 */
@GrpcService
class VillageGrpcService(
    private val listVillagesUseCase: ListVillagesUseCase,
    private val createVillageUseCase: CreateVillageUseCase,
) : VillageServiceGrpc.VillageServiceImplBase() {
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
        val villages = listVillagesUseCase.invoke()
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
                .build()
        }
        val listVillagesResponse = ListVillagesResponse.newBuilder().addAllVillages(villageResponseList).build()
        responseObserver.let { r ->
            r.onNext(listVillagesResponse)
            r.onCompleted()
        }
    }
}

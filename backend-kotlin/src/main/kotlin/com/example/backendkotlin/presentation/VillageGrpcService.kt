package com.example.backendkotlin.presentation

import com.example.backendkotlin.generated.grpc.*
import com.example.backendkotlin.usecase.ListVillagesUseCase
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

/**
 * 村に関するgRPCサービス
 */
@GrpcService
class VillageGrpcService(
    private val listVillagesUseCase: ListVillagesUseCase
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
        val response = CreateVillageResponse.newBuilder()
            .setId("12345")
            .setName("VillageName")
            .setUserNumber(10)
            .setCitizenCount(4)
            .setWerewolfCount(2)
            .setFortuneTellerCount(1)
            .setKnightCount(1)
            .setPsychicCount(1)
            .setMadmanCount(1)
            .setIsInitialActionActive(true)
            .build()
        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
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
        val response = ListVillagesResponse.newBuilder().addAllVillages(
            listVillagesUseCase.invoke().map {
                VillageResponse.newBuilder()
                    .setId(it.id.value.toString())
                    .setName(it.name)
                    .setUserNumber(it.citizenCount + it.werewolfCount + it.fortuneTellerCount + it.knightCount + it.psychicCount + it.madmanCount)
                    .setCitizenCount(it.citizenCount)
                    .setWerewolfCount(it.werewolfCount)
                    .setFortuneTellerCount(it.fortuneTellerCount)
                    .setKnightCount(it.knightCount)
                    .setPsychicCount(it.psychicCount)
                    .setMadmanCount(it.madmanCount)
                    .setIsInitialActionActive(it.isInitialActionActive)
                    .build()
            }
        ).build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}

package com.example.backendkotlin.presentation

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
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
import java.util.UUID

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
        val newVillageId = VillageId(UUID.randomUUID())
        val newVillageRequest = Village(
            id = newVillageId,
            name = request.name,
            citizenCount = request.citizenCount,
            werewolfCount = request.werewolfCount,
            fortuneTellerCount = request.fortuneTellerCount,
            knightCount = request.knightCount,
            psychicCount = request.psychicCount,
            madmanCount = request.madmanCount,
            isInitialActionActive = request.isInitialActionActive,
        )
        val insertedVillage = createVillageUseCase.invoke(newVillageRequest, request.password).let {
            CreateVillageResponse.newBuilder()
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
        responseObserver?.onNext(insertedVillage)
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
            },
        ).build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}

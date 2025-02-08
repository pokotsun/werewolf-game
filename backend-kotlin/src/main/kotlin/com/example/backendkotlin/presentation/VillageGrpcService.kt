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
        val newVillageRequest = request.let { r ->
            Village(
                id = newVillageId,
                name = r.name,
                citizenCount = r.citizenCount,
                werewolfCount = r.werewolfCount,
                fortuneTellerCount = r.fortuneTellerCount,
                knightCount = r.knightCount,
                psychicCount = r.psychicCount,
                madmanCount = r.madmanCount,
                isInitialActionActive = r.isInitialActionActive,
            )
        }
        val insertedVillage = createVillageUseCase.invoke(newVillageRequest, request.password).let { res ->
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
                .build()
        }
        responseObserver.let { r ->
            r.onNext(insertedVillage)
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
        val response = ListVillagesResponse.newBuilder().addAllVillages(
            listVillagesUseCase.invoke().map { res ->
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
            },
        ).build()
        responseObserver.let { r ->
            r.onNext(response)
            r.onCompleted()
        }
    }
}

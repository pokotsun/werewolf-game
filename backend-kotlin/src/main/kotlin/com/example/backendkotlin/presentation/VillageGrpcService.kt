package com.example.backendkotlin.presentation

import com.example.backendkotlin.generated.grpc.*
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class VillageGrpcService : VillageServiceGrpc.VillageServiceImplBase() {
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

    override fun listVillages(request: ListVillagesRequest, responseObserver: StreamObserver<ListVillagesResponse>) {
        val response = ListVillagesResponse.newBuilder().addVillages(
            VillageResponse.newBuilder()
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
                .build(),
        ).build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}

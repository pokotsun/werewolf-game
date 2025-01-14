package com.example.backendkotlin.usecase

import com.example.backendkotlin.generated.grpc.CreateVillageRequest
import com.example.backendkotlin.generated.grpc.CreateVillageResponse
import com.example.backendkotlin.generated.grpc.VillageServiceGrpc
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class CreateVillageUsecase : VillageServiceGrpc.VillageServiceImplBase() {
    override fun createVillage(
        request: CreateVillageRequest,
        responseObserver: StreamObserver<CreateVillageResponse>,
    ) {
        val response = CreateVillageResponse.newBuilder()
            .setId("12345")
            .setName("VillageName")
            .setCreatedAt("2022-01-01T00:00:00")
            .setUpdatedAt("2022-01-01T00:00:15")
            .build()
        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}

package com.example.backendkotlin.presentation

import com.example.backendkotlin.domain.WerewolfErrorCode
import com.example.backendkotlin.domain.WerewolfException
import com.google.protobuf.GeneratedMessage
import io.grpc.Status
import io.grpc.stub.StreamObserver

interface GrpcServiceExceptionHandler {
    fun <T : GeneratedMessage> handleException(responseObserver: StreamObserver<T>, action: () -> Unit) {
        try {
            action()
        } catch (e: WerewolfException) {
            when (e.code) {
                WerewolfErrorCode.RESOURCE_NOT_FOUND -> {
                    val message = "The village does not exist"
                    responseObserver.onError(Status.NOT_FOUND.withDescription(message).asRuntimeException())
                }
                WerewolfErrorCode.VILLAGE_PASSWORD_IS_WRONG -> {
                    val message = "The village password is wrong"
                    responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(message).asRuntimeException())
                }
            }
        } catch (e: Exception) {
            val message = "An error occurred"
            responseObserver.onError(Status.UNKNOWN.withDescription(message).asRuntimeException())
        }
    }
}

package com.example.backendkotlin.presentation

import com.example.backendkotlin.domain.WerewolfErrorCode
import com.example.backendkotlin.domain.WerewolfException
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.grpc.Status
import io.grpc.StatusRuntimeException

interface GrpcServiceExceptionHandler {
    fun <T> handleException(action: () -> T): Result<T, StatusRuntimeException> {
        try {
            return Ok(action())
        } catch (e: WerewolfException) {
            when (e.code) {
                WerewolfErrorCode.RESOURCE_NOT_FOUND -> {
                    val message = "The village does not exist"
                    return Err(Status.NOT_FOUND.withDescription(message).asRuntimeException())
                }
                WerewolfErrorCode.VILLAGE_PASSWORD_IS_WRONG -> {
                    val message = "The village password is wrong"
                    return Err(Status.INVALID_ARGUMENT.withDescription(message).asRuntimeException())
                }
            }
        } catch (e: Exception) {
            val message = "An error occurred"
            return Err(Status.UNKNOWN.withDescription(message).asRuntimeException())
        }
    }
}

package com.example.backendkotlin.usecase

import com.example.backendkotlin.presentation.response.VillageResponse
import com.example.backendkotlin.repository.VillageRepository
import org.springframework.stereotype.Service

@Service
class ListAllVillagesUseCase(private val villageRepository: VillageRepository) {
    fun invoke(): List<VillageResponse> {
        return villageRepository.selectAllVillages()
    }
}

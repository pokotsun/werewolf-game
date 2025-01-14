package com.example.backendkotlin.presentation

import com.example.backendkotlin.presentation.response.VillageResponse
import com.example.backendkotlin.usecase.ListAllVillagesUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class VillageController(
    private val listAllVillagesUseCase: ListAllVillagesUseCase,
) {
    @GetMapping("/villages")
    fun listAllVillages(): List<VillageResponse> {
        return listAllVillagesUseCase.invoke()
    }
}

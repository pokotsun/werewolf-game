package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageRepository
import org.springframework.stereotype.Service

/**
 * 村の一覧を取得するユースケース
 */
@Service
class ListVillagesUseCase(private val villageRepository: VillageRepository) {
    /**
     * 村の一覧を取得する
     *
     * @return 村の一覧
     */
    fun invoke(): List<Village> {
        return villageRepository.selectAllVillages()
    }
}

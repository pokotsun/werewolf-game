package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageRepository
import org.springframework.stereotype.Service

/**
 * 村の一覧を取得するユースケース
 */
@Service
class ListVillagesUseCase(
    private val villageRepository: VillageRepository,
) {
    /**
     * 村ごとの現在の参加者数を取得し、それを含めた村の一覧を返す
     *
     * @param isRecruitedOnly 募集中の村のみ取得するかどうか
     *
     * @return 村の一覧
     */
    fun invoke(isRecruitedOnly: Boolean = false): List<Village> {
        return villageRepository.selectAllVillages(isRecruitedOnly)
    }
}

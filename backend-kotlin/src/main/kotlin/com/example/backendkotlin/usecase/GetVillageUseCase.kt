package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class GetVillageUseCase(
    private val villageRepository: VillageRepository,
) {
    /**
     * 村を取得する
     *
     * @param villageIdString 村ID
     *
     * @return 村
     */
    fun invoke(villageIdString: String): Village {
        // 村を取得
        val villageId = VillageId.generate(villageIdString)
        val villageAndPassword = villageRepository.selectVillageById(villageId)
            ?: throw ResourceNotFoundException("村が存在しません")
        val result = villageAndPassword.first
        return result
    }
}

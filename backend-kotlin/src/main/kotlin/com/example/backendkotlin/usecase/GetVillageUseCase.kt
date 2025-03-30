package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.domain.WerewolfErrorCode
import com.example.backendkotlin.domain.WerewolfException
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
        val villageId = VillageId.from(villageIdString)
        val villageAndPassword = villageRepository.selectVillageById(villageId)
            ?: throw WerewolfException(WerewolfErrorCode.RESOURCE_NOT_FOUND, "村が存在しません")
        val result = villageAndPassword.first
        return result
    }
}

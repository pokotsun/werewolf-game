package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import org.springframework.stereotype.Service

@Service
class GetVillageUseCase {
    /**
     * 村を取得する
     *
     * @param villageIdString 村ID
     *
     * @return 村
     */
    fun invoke(villageIdString: String): Village {
        // 村を取得
        // Todo: 村を取得する処理を実装する
        val result = Village(
            id = VillageId.generate(),
            name = "村名",
            citizenCount = 1,
            werewolfCount = 1,
            fortuneTellerCount = 1,
            knightCount = 1,
            psychicCount = 1,
            madmanCount = 1,
            isInitialActionActive = true,
            gameMasterUserId = UserId.generate(),
            isRecruited = true,
        )
        return result
    }
}

package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.RUserVillageRepository
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageRepository
import org.springframework.stereotype.Service

/**
 * 村の一覧を取得するユースケース
 */
@Service
class ListVillagesUseCase(
    private val villageRepository: VillageRepository,
    private val rUserVillageRepository: RUserVillageRepository,
) {
    /**
     * 村ごとの現在の参加者数を取得し、それを含めた村の一覧を返す
     *
     * @return 村の一覧
     */
    fun invoke(): List<Village> {
        // current_user_numberが初期状態の村一覧を取得
        val defaultVillages = villageRepository.selectAllVillages()
        val villageIds = defaultVillages.map { it.id }

        // 村ごとの現在の参加者を取得
        val userVillageIdMap = rUserVillageRepository.selectByVillageIds(villageIds)

        // 村ごとの現在の参加者数を設定
        val result = defaultVillages.map { village ->
            val currentUserNumber = try {
                userVillageIdMap.getValue(village.id).size
            } catch (e: NoSuchElementException) {
                // RUserVillageへの登録漏れを検知した場合はその旨をログに出力しつつデフォルトの値を返す
                println("RUserVillageへの登録漏れを検知しました。villageId: ${village.id}")
                village.currentUserNumber
            }
            Village(
                id = village.id,
                name = village.name,
                citizenCount = village.citizenCount,
                werewolfCount = village.werewolfCount,
                fortuneTellerCount = village.fortuneTellerCount,
                knightCount = village.knightCount,
                psychicCount = village.psychicCount,
                madmanCount = village.madmanCount,
                isInitialActionActive = village.isInitialActionActive,
                gameMasterUserId = village.gameMasterUserId,
                currentUserNumber = currentUserNumber,
            )
        }
        return result
    }
}

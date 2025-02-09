package com.example.backendkotlin.domain

/**
 * RUserVillageテーブルに関するRepository
 */
interface RUserVillageRepository {
    /**
     * RUserVillageテーブルにレコードを登録する
     *
     * @param userId ユーザーID
     * @param villageId 村ID
     */
    fun save(userId: UserId, villageId: VillageId): Pair<UserId, VillageId>
}

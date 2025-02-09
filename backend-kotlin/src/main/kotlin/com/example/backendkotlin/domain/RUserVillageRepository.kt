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

    /**
     * 村IDからその村に参加しているユーザーID一覧を取得する
     *
     * @param villageId 村ID
     * @return ユーザーID一覧
     */
    fun selectByVillageId(villageId: VillageId): List<UserId>
}

package com.example.backendkotlin.domain

/**
 * Villageに関するRepository
 */
interface VillageRepository {
    /**
     * 村の一覧を取得する
     * 村ごとの現在の参加者数を取得し、それを含めた村の一覧を返す
     *
     * @param isRecruitedOnly 募集中の村のみ取得するかどうか
     *
     * @return 村の一覧
     */
    fun selectAllVillages(isRecruitedOnly: Boolean): List<Village>

    /**
     * 村を取得する
     *
     * @param villageId 村ID
     *
     * @return 村
     */
    fun selectVillageById(villageId: VillageId): Pair<Village, HashedPassword>?

    /**
     * 村を作成する
     * @condition ゲームマスターがすでにUserTableに作成されていること
     *
     * @param village 村
     * @param hashedPassword パスワードとそのランダムなsalt
     *
     * @return 作成した村
     */
    fun createVillage(village: Village, hashedPassword: HashedPassword): Village
}

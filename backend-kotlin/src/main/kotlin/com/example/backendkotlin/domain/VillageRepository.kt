package com.example.backendkotlin.domain

/**
 * Villageに関するRepository
 */
interface VillageRepository {
    /**
     * 村の一覧を取得する
     * 村ごとの現在の参加者数を取得し、それを含めた村の一覧を返す
     *
     * @return 村の一覧
     */
    fun selectAllVillages(): List<Village>

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

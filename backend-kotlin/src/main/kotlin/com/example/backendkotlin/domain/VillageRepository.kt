package com.example.backendkotlin.domain

/**
 * Villageに関するRepository
 */
interface VillageRepository {
    /**
     * 村の一覧を取得する
     *
     * @return 村の一覧
     */
    fun selectAllVillages(): List<Village>

    /**
     * 村を作成する
     * @condition ゲームマスターがすでにUserTableに作成されていること
     *
     * @param village 村
     * @param passwordHash パスワードのハッシュ
     * @param salt パスワードのソルト
     *
     * @return 作成した村
     */
    fun createVillage(village: Village, passwordHash: String, salt: String): Village
}

package com.example.backendkotlin.domain

import java.util.UUID

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
     * @param gameMasterUserId ゲームマスターのユーザID
     *
     * @return 作成した村
     */
    fun createVillage(village: Village, passwordHash: String, salt: String, gameMasterUserId: UUID): Village
}

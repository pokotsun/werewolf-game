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
}
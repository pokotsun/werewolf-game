package com.example.backendkotlin.domain

/**
 * Gameに関するRepository
 */
interface GameRepository {
    /**
     * ゲームを開始する
     *
     * @param villageId 村ID
     * @param game ゲーム
     * @param players プレイヤーリスト
     * @return ゲーム
     */
    fun createGame(villageId: VillageId, game: Game, players: List<Player>): Game
}

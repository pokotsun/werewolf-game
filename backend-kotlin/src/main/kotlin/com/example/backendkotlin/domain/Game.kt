package com.example.backendkotlin.domain

/**
 * 人狼ゲームを表すエンティティ
 *
 * @param id ゲームID
 * @param isPlaying ゲームが進行中かどうか
 * @param isInitialActionActive 初期アクションが有効かどうか
 * @param day ゲームの進行日数
 * @param term ゲームの進行状態(昼か夜か)
 */
data class Game(
    val id: GameId,
    val isPlaying: Boolean,
    val isInitialActionActive: Boolean,
    val day: Int,
    val term: GameTerm,
)

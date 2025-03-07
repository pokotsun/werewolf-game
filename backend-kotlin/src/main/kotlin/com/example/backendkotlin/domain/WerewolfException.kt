package com.example.backendkotlin.domain

/**
 * 人狼ゲームアプリケーションの例外
 *
 * @param code エラーコード
 * @param message エラーメッセージ
 */
data class WerewolfException(
    val code: WerewolfErrorCode,
    override val message: String,
) : RuntimeException(message)

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
    override val cause: Throwable?,
) : RuntimeException(message) {
    constructor(code: WerewolfErrorCode, message: String) : this(code, message, null)
}

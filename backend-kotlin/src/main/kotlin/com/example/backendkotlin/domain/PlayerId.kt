package com.example.backendkotlin.domain

import java.util.UUID

/**
 * プレイヤーのIDを表すクラス
 *
 * @param value プレイヤーのID
 */
data class PlayerId(val value: UUID) {
    companion object {
        /**
         * プレイヤーIDを生成する
         *
         * @return プレイヤーID
         */
        fun generate(): PlayerId {
            return PlayerId(UUID.randomUUID())
        }

        /**
         * プレイヤーIDを生成する
         *
         * @param value プレイヤーIDの文字列
         * @return プレイヤーID
         */
        fun from(value: String): PlayerId {
            return PlayerId(UUID.fromString(value))
        }
    }
}

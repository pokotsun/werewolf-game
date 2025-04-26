package com.example.backendkotlin.domain

import java.util.UUID

data class GameId(val value: UUID) {
    companion object {
        /**
         * ゲームIDを生成する
         *
         * @return ゲームID
         */
        fun generate(): GameId {
            return GameId(UUID.randomUUID())
        }

        /**
         * ゲームIDを生成する
         *
         * @param value ゲームIDの文字列
         * @return ゲームID
         */
        fun from(value: String): GameId {
            return GameId(UUID.fromString(value))
        }
    }
}

package com.example.backendkotlin.domain

import java.util.UUID

data class UserId(val value: UUID) {
    companion object {
        /**
         * ユーザーIDを生成する
         *
         * @return ユーザーID
         */
        fun generate(): UserId {
            return UserId(UUID.randomUUID())
        }

        /**
         * 文字列からユーザーIDを生成する
         *
         * @param userIdString ユーザーIDの文字列
         * @return ユーザーID
         */
        fun from(userIdString: String): UserId {
            return UserId(UUID.fromString(userIdString))
        }
    }
}

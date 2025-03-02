package com.example.backendkotlin.domain

import java.util.UUID

data class VillageId(val value: UUID) {
    companion object {
        /**
         * 村IDを生成する
         *
         * @return 村ID
         */
        fun generate(): VillageId {
            return VillageId(UUID.randomUUID())
        }

        /**
         * 村IDを生成する
         *
         * @param value 村IDの文字列
         * @return 村ID
         */
        fun generate(value: String): VillageId {
            return VillageId(UUID.fromString(value))
        }
    }
}

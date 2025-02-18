package com.example.backendkotlin.domain

import java.util.UUID

data class VillageId(val value: UUID) {
    companion object {
        fun generate(): VillageId {
            return VillageId(UUID.randomUUID())
        }
    }
}

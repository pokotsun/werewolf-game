package com.example.backendkotlin.infrastructure.db.table

import org.jetbrains.exposed.dao.id.UUIDTable

object GameTable : UUIDTable("game") {
    val villageId = uuid("village_id").references(VillageTable.id)
    val isPlaying = bool("is_playing").default(true)
    val isInitialActionActive = bool("is_initial_action_active").default(false)
}

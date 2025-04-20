package com.example.backendkotlin.infrastructure.db.table

import org.jetbrains.exposed.dao.id.UUIDTable

object PlayerTable : UUIDTable("player") {
    val userId = uuid("user_id").references(UserTable.id)
    val gameId = uuid("game_id").references(GameTable.id)
    val actorId = integer("actor_id")
    val point = integer("point")
    val isDead = bool("is_dead").default(false)
}

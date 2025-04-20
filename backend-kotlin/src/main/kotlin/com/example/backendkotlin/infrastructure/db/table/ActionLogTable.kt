package com.example.backendkotlin.infrastructure.db.table

import org.jetbrains.exposed.dao.id.UUIDTable

object ActionLogTable : UUIDTable("action_log") {
    val gameId = uuid("game_id").references(GameTable.id)
    val playerId = uuid("player_id").references(PlayerTable.id)
    val targetPlayerId = uuid("target_player_id").references(PlayerTable.id)
    val gameStatusId = uuid("game_status_id").references(GameStatusTable.id)
    val round = integer("round")
}

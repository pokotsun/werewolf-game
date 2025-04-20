package com.example.backendkotlin.infrastructure.db.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object GameStatusTable : UUIDTable("game_status") {
    val gameId = uuid("game_id").references(GameTable.id)
    val day = integer("day")
    val term = integer("term")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}

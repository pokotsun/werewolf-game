package com.example.backendkotlin.infrastructure.db.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentTimestamp
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object VillageTable : UUIDTable("village") {
    val name = varchar("name", 255)
    val password = varchar("password", 255)
    val citizenCount = decimal("citizen_count", 2, 0)
    val werewolfCount = decimal("werewolf_count", 1, 0)
    val fortuneTellerCount = decimal("fortune_teller_count", 1, 0)
    val knightCount = decimal("knight_count", 1, 0)
    val psychicCount = decimal("psychic_count", 1, 0)
    val madmanCount = decimal("madman_count", 1, 0)
    val isInitialActionActive = bool("is_initial_action_active")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}
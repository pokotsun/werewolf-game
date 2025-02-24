package com.example.backendkotlin.infrastructure.db.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object VillageTable : UUIDTable("village") {
    val name = varchar("name", length = 255)
    val salt = varchar("salt", length = 255)
    val passwordHash = varchar("password_hash", length = 255)
    val citizenCount = integer("citizen_count")
    val werewolfCount = integer("werewolf_count")
    val fortuneTellerCount = integer("fortune_teller_count")
    val knightCount = integer("knight_count")
    val psychicCount = integer("psychic_count")
    val madmanCount = integer("madman_count")
    val isInitialActionActive = bool("is_initial_action_active")
    val gameMasterUserId = uuid("game_master_user_id").references(UserTable.id, onDelete = ReferenceOption.CASCADE)
    val isRecruited = bool("is_recruited").default(true)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}

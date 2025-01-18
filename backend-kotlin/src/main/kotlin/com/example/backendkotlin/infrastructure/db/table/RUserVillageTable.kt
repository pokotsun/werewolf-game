package com.example.backendkotlin.infrastructure.db.table

import org.jetbrains.exposed.sql.Table

object RUserVillageTable : Table("r_user_village") {
    val userId = uuid("user_id").references(UserTable.id)
    val villageId = uuid("village_id").references(VillageTable.id)

    override val primaryKey = PrimaryKey(userId, villageId, name = "PK_User_Village")
}

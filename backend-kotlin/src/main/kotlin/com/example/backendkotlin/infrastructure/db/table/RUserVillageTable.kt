package com.example.backendkotlin.infrastructure.db.table

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object RUserVillageTable : Table("r_user_village") {
    val userId = uuid("user_id").references(UserTable.id, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE)
    val villageId = uuid("village_id").references(VillageTable.id, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(userId, villageId, name = "PK_User_Village")
}

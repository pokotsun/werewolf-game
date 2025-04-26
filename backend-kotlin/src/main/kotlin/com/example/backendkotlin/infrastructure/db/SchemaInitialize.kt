package com.example.backendkotlin.infrastructure.db

import com.example.backendkotlin.infrastructure.db.table.ActionLogTable
import com.example.backendkotlin.infrastructure.db.table.BitingResultLogTable
import com.example.backendkotlin.infrastructure.db.table.GameStatusTable
import com.example.backendkotlin.infrastructure.db.table.GameTable
import com.example.backendkotlin.infrastructure.db.table.PlayerTable
import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable
import com.example.backendkotlin.infrastructure.db.table.UserTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import com.example.backendkotlin.infrastructure.db.table.VotingResultLogTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class SchemaInitialize : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        SchemaUtils.create(
            UserTable,
            VillageTable,
            RUserVillageTable,
            GameTable,
            GameStatusTable,
            PlayerTable,
            ActionLogTable,
            BitingResultLogTable,
            VotingResultLogTable,
        )
    }
}

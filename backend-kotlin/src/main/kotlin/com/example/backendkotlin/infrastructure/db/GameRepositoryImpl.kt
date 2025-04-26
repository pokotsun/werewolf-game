package com.example.backendkotlin.infrastructure.db

import com.example.backendkotlin.domain.Game
import com.example.backendkotlin.domain.GameRepository
import com.example.backendkotlin.domain.Player
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastructure.db.table.GameStatusTable
import com.example.backendkotlin.infrastructure.db.table.GameTable
import com.example.backendkotlin.infrastructure.db.table.PlayerTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository

@Repository
class GameRepositoryImpl() : GameRepository {
    /**
     * {@inheritDoc}
     */
    override fun createGame(villageId: VillageId, game: Game, players: List<Player>): Game {
        // GameをDBに保存
        transaction {
            VillageTable.update(where = { VillageTable.id eq villageId.value }) {
                it[isRecruited] = false
            }
            GameTable.insert {
                it[GameTable.id] = game.id.value
                it[GameTable.villageId] = villageId.value
                it[isPlaying] = game.isPlaying
                it[isInitialActionActive] = game.isInitialActionActive
            }
            GameStatusTable.insert {
                it[gameId] = game.id.value
                it[day] = game.day
                it[term] = game.term.ordinal
            }
            PlayerTable.batchInsert(players) { player ->
                this[PlayerTable.gameId] = game.id.value
                this[PlayerTable.userId] = player.user.id.value
                this[PlayerTable.actorId] = player.actor.id
                this[PlayerTable.isDead] = player.isDead
            }
        }
        return game
    }
}

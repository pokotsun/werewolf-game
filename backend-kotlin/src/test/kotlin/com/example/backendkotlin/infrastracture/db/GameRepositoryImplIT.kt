package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.Actor
import com.example.backendkotlin.domain.Game
import com.example.backendkotlin.domain.GameId
import com.example.backendkotlin.domain.GameRepository
import com.example.backendkotlin.domain.GameTerm
import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.Player
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastructure.db.table.UserTable
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import com.example.backendkotlin.util.KSelect
import io.kotest.assertions.throwables.shouldNotThrowAny
import org.instancio.Instancio
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GameRepositoryImplIT(
    private val gameRepository: GameRepository,
) : DescribeSpecUsingPostgreSQLTestContainer() {
    init {
        this.describe("createGame") {
            it("ゲームを作成できる") {
                // given
                val villageId = Instancio.create(VillageId::class.java)
                val village = Instancio.of(Village::class.java)
                    .set(KSelect.field(Village::id), villageId)
                    .set(KSelect.field(Village::werewolfCount), 1)
                    .set(KSelect.field(Village::citizenCount), 2)
                    .set(KSelect.field(Village::fortuneTellerCount), 0)
                    .set(KSelect.field(Village::knightCount), 0)
                    .set(KSelect.field(Village::psychicCount), 0)
                    .set(KSelect.field(Village::madmanCount), 0)
                    .set(KSelect.field(Village::isRecruited), true)
                    .create()
                val villagePassword = Instancio.create(HashedPassword::class.java)
                val gameMasterId = Instancio.create(UserId::class.java)
                val gameMaster = Instancio.of(User::class.java)
                    .set(KSelect.field(User::id), gameMasterId)
                    .create()
                val user1 = Instancio.create(User::class.java)
                val user2 = Instancio.create(User::class.java)
                val game = Game(
                    id = Instancio.create(GameId::class.java),
                    isPlaying = true,
                    isInitialActionActive = false,
                    day = 1,
                    term = GameTerm.NIGHT,
                )
                val player1 = Instancio.of(Player::class.java)
                    .set(KSelect.field(Player::user), gameMaster)
                    .set(KSelect.field(Player::actor), Actor.WEREWOLF)
                    .create()
                val player2 = Instancio.of(Player::class.java)
                    .set(KSelect.field(Player::user), user1)
                    .set(KSelect.field(Player::actor), Actor.CITIZEN)
                    .create()
                val player3 = Instancio.of(Player::class.java)
                    .set(KSelect.field(Player::user), user2)
                    .set(KSelect.field(Player::actor), Actor.CITIZEN)
                    .create()
                val players = listOf(
                    player1,
                    player2,
                    player3,
                )

                // and
                transaction {
                    UserTable.insert {
                        it[UserTable.id] = gameMasterId.value
                        it[UserTable.name] = gameMaster.name
                        it[UserTable.passwordHash] = Instancio.create(HashedPassword::class.java).value
                        it[UserTable.isActive] = true
                    }
                    VillageTable.insert {
                        it[VillageTable.id] = villageId.value
                        it[VillageTable.name] = village.name
                        it[VillageTable.passwordHash] = villagePassword.value
                        it[VillageTable.werewolfCount] = village.werewolfCount
                        it[VillageTable.citizenCount] = village.citizenCount
                        it[VillageTable.fortuneTellerCount] = village.fortuneTellerCount
                        it[VillageTable.knightCount] = village.knightCount
                        it[VillageTable.psychicCount] = village.psychicCount
                        it[VillageTable.madmanCount] = village.madmanCount
                        it[VillageTable.isInitialActionActive] = false
                        it[VillageTable.gameMasterUserId] = gameMasterId.value
                        it[VillageTable.isRecruited] = village.isRecruited
                    }
                    UserTable.insert {
                        it[UserTable.id] = user1.id.value
                        it[UserTable.name] = user1.name
                        it[UserTable.passwordHash] = Instancio.create(HashedPassword::class.java).value
                        it[UserTable.isActive] = true
                    }
                    UserTable.insert {
                        it[UserTable.id] = user2.id.value
                        it[UserTable.name] = user2.name
                        it[UserTable.passwordHash] = Instancio.create(HashedPassword::class.java).value
                        it[UserTable.isActive] = true
                    }
                }

                // when, then
                shouldNotThrowAny {
                    gameRepository.createGame(villageId, game, players)
                }
            }
        }
    }
}

package com.example.backendkotlin.domain

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.instancio.Instancio

class PlayerUT : DescribeSpec() {

    override suspend fun beforeSpec(spec: Spec) {
        mockkObject(PlayerId)
    }

    override suspend fun afterSpec(spec: Spec) {
        unmockkObject(PlayerId)
    }

    init {
        describe("createPlayers") {
            it("プレイヤーを作成できる") {
                // given
                val user1 = Instancio.create(User::class.java)
                val user2 = Instancio.create(User::class.java)
                val user3 = Instancio.create(User::class.java)
                val actor1 = Actor.WEREWOLF
                val actor2 = Actor.CITIZEN
                val actor3 = Actor.FORTUNE_TELLER
                val users = listOf(user1, user2, user3)
                val shuffledActors = listOf(actor1, actor2, actor3)
                val playerId1 = Instancio.create(PlayerId::class.java)
                val playerId2 = Instancio.create(PlayerId::class.java)
                val playerId3 = Instancio.create(PlayerId::class.java)
                val expected = listOf(
                    Player(playerId1, user1, actor1, false),
                    Player(playerId2, user2, actor2, false),
                    Player(playerId3, user3, actor3, false),
                )

                // and
                every { PlayerId.generate() } returnsMany listOf(playerId1, playerId2, playerId3)

                // when
                val result = Player.createPlayers(users, shuffledActors)

                // then
                result shouldBe expected
                verify(exactly = 3) {
                    PlayerId.generate()
                }
            }
        }
    }
}

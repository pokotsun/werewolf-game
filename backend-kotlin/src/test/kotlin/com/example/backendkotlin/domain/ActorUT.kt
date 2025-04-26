package com.example.backendkotlin.domain

import com.example.backendkotlin.util.KSelect
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import org.instancio.Instancio

/**
 * Actorのユニットテストクラス
 *
 */
class ActorUT : DescribeSpec() {
    init {
        describe("createShuffledActorListメソッドのテスト") {
            it("役職リストを作成できる") {
                // given
                val village = Instancio.of(Village::class.java)
                    .set(KSelect.field(Village::werewolfCount), 2)
                    .set(KSelect.field(Village::citizenCount), 3)
                    .set(KSelect.field(Village::fortuneTellerCount), 1)
                    .set(KSelect.field(Village::knightCount), 1)
                    .set(KSelect.field(Village::psychicCount), 1)
                    .set(KSelect.field(Village::madmanCount), 1)
                    .create()
                val expected = listOf(
                    Actor.WEREWOLF,
                    Actor.WEREWOLF,
                    Actor.CITIZEN,
                    Actor.CITIZEN,
                    Actor.CITIZEN,
                    Actor.FORTUNE_TELLER,
                    Actor.KNIGHT,
                    Actor.PSYCHIC,
                    Actor.MADMAN,
                )

                // when
                val result = Actor.createShuffledActorList(village)

                // then
                result shouldContainExactlyInAnyOrder expected
            }
        }
    }
}

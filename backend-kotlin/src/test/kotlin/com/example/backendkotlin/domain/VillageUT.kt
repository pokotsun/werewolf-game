package com.example.backendkotlin.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.UUID

class VillageUT : DescribeSpec() {
    init {
        describe("Village") {
            it("全ての値が条件を満たしているので正常に初期化できる") {
                // given
                val id = VillageId(UUID.randomUUID())
                val name = "Village 1"
                val citizenCount = 10
                val werewolfCount = 2
                val fortuneTellerCount = 1
                val knightCount = 1
                val psychicCount = 1
                val madmanCount = 1
                val isInitialActionActive = true

                // when, then
                shouldNotThrowAny {
                    Village(
                        id = id,
                        name = name,
                        citizenCount = citizenCount,
                        werewolfCount = werewolfCount,
                        fortuneTellerCount = fortuneTellerCount,
                        knightCount = knightCount,
                        psychicCount = psychicCount,
                        madmanCount = madmanCount,
                        isInitialActionActive = isInitialActionActive,
                    )
                }
            }
            it("userNumberが全役職の数の合計になっている") {
                // given
                val id = VillageId(UUID.randomUUID())
                val name = "Village 1"
                val citizenCount = 10
                val werewolfCount = 2
                val fortuneTellerCount = 1
                val knightCount = 1
                val psychicCount = 1
                val madmanCount = 1
                val isInitialActionActive = true
                val expectedUserNumber = citizenCount + werewolfCount + fortuneTellerCount + knightCount + psychicCount + madmanCount

                // when
                val village = Village(
                    id = id,
                    name = name,
                    citizenCount = citizenCount,
                    werewolfCount = werewolfCount,
                    fortuneTellerCount = fortuneTellerCount,
                    knightCount = knightCount,
                    psychicCount = psychicCount,
                    madmanCount = madmanCount,
                    isInitialActionActive = isInitialActionActive,
                )
                val actual = village.userNumber

                // then
                actual shouldBe expectedUserNumber
            }

            it("市民の数が0未満の場合は例外が発生する") {
                // given
                val id = VillageId(UUID.randomUUID())
                val name = "Village 1"
                val citizenCount = -1
                val werewolfCount = 2
                val fortuneTellerCount = 1
                val knightCount = 1
                val psychicCount = 1
                val madmanCount = 1
                val isInitialActionActive = true

                // when
                val exception = shouldThrow<IllegalArgumentException> {
                    Village(
                        id = id,
                        name = name,
                        citizenCount = citizenCount,
                        werewolfCount = werewolfCount,
                        fortuneTellerCount = fortuneTellerCount,
                        knightCount = knightCount,
                        psychicCount = psychicCount,
                        madmanCount = madmanCount,
                        isInitialActionActive = isInitialActionActive,
                    )
                }

                // then
                exception.message shouldBe "citizenCount must be greater than or equal to 0"
            }
            it("複数のメンバ変数が条件を満たしていなくても例外が発生し先の方の例外がスローされる") {
                // given
                val id = VillageId(UUID.randomUUID())
                val name = "Village 1"
                val citizenCount = 10
                val werewolfCount = -1
                val fortuneTellerCount = -100000
                val knightCount = 1
                val psychicCount = 1
                val madmanCount = 1
                val isInitialActionActive = true

                // when
                val exception = shouldThrow<IllegalArgumentException> {
                    Village(
                        id = id,
                        name = name,
                        citizenCount = citizenCount,
                        werewolfCount = werewolfCount,
                        fortuneTellerCount = fortuneTellerCount,
                        knightCount = knightCount,
                        psychicCount = psychicCount,
                        madmanCount = madmanCount,
                        isInitialActionActive = isInitialActionActive,
                    )
                }

                // then
                exception.message shouldBe "werewolfCount must be greater than or equal to 0"
            }
        }
    }
}

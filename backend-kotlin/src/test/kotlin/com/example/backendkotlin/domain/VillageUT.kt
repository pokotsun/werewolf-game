package com.example.backendkotlin.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertDoesNotThrow
import java.util.UUID

class VillageUT: DescribeSpec() {
    init {
        describe("Village") {
            it("全ての値が条件を満たしているので正常に初期化できる") {
                assertDoesNotThrow {
                    Village(
                        id = VillageId(UUID.randomUUID()),
                        name = "Village 1",
                        citizenCount = 10,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = true
                    )
                }
            }
            it("市民の数が0未満の場合は例外が発生する") {
                // when
                val exception = shouldThrow<IllegalArgumentException> {
                    Village(
                        id = VillageId(UUID.randomUUID()),
                        name = "Village 1",
                        citizenCount = -1,
                        werewolfCount = 2,
                        fortuneTellerCount = 1,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = true
                    )
                }

                // then
                exception.message shouldBe "citizenCount must be greater than or equal to 0"
            }
            it("複数のメンバ変数が条件を満たしていなくても例外が発生し先の方の例外がスローされる") {
                // when
                val exception = shouldThrow<IllegalArgumentException> {
                    Village(
                        id = VillageId(UUID.randomUUID()),
                        name = "Village 1",
                        citizenCount = 10,
                        werewolfCount = -1,
                        fortuneTellerCount = -100000,
                        knightCount = 1,
                        psychicCount = 1,
                        madmanCount = 1,
                        isInitialActionActive = true
                    )
                }

                // then
                exception.message shouldBe "werewolfCount must be greater than or equal to 0"
            }
        }
    }
}
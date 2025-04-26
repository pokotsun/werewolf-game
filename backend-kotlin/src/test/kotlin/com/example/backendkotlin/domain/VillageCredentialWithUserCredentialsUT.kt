package com.example.backendkotlin.domain

import com.example.backendkotlin.util.KSelect
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.instancio.Instancio

/**
 * VillageCredentialWithUserCredentialsエンティティのテスト
 */
class VillageCredentialWithUserCredentialsUT : DescribeSpec() {
    init {
        this.describe("VillageCredentialWithUserCredentials") {
            context("doesJoinAllUsers") {
                it("村に全員参加している場合はtrueを返す") {
                    // given
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::citizenCount), 3)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 0)
                        .create()
                    val userCount = village.userNumber
                    val users = List(userCount) {
                        Instancio.create(UserCredential::class.java)
                    }
                    val villageCredentialWithUserCredentials = Instancio.of(VillageCredentialWithUserCredentials::class.java)
                        .set(KSelect.field(VillageCredentialWithUserCredentials::village), village)
                        .set(KSelect.field(VillageCredentialWithUserCredentials::userCredentials), users)
                        .create()

                    // when
                    val result = villageCredentialWithUserCredentials.doesJoinAllUsers()

                    // then
                    result shouldBe true
                }

                it("村に全員参加していない場合はfalseを返す") {
                    // given
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::werewolfCount), 2)
                        .set(KSelect.field(Village::citizenCount), 3)
                        .set(KSelect.field(Village::fortuneTellerCount), 1)
                        .set(KSelect.field(Village::knightCount), 1)
                        .set(KSelect.field(Village::psychicCount), 1)
                        .set(KSelect.field(Village::madmanCount), 0)
                        .create()
                    // 異なるユーザー数を持つ村を作成
                    val userCount = village.userNumber - 1
                    val users = List(userCount) {
                        Instancio.create(UserCredential::class.java)
                    }
                    val villageCredentialWithUserCredentials = Instancio.of(VillageCredentialWithUserCredentials::class.java)
                        .set(KSelect.field(VillageCredentialWithUserCredentials::village), village)
                        .set(KSelect.field(VillageCredentialWithUserCredentials::userCredentials), users)
                        .create()

                    // when
                    val result = villageCredentialWithUserCredentials.doesJoinAllUsers()

                    // then
                    result shouldBe false
                }
            }
        }
    }
}

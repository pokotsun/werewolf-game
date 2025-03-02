package com.example.backendkotlin.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.testcontainers.shaded.org.bouncycastle.cms.RecipientId.password

/**
 * HashedPasswordWithRandomSaltエンティティのテスト
 */
class HashedPasswordUT : DescribeSpec() {

    init {
        this.describe("create") {
            it("照合可能なパスワードを生成できる") {
                // given
                val password = "password"

                // then
                shouldNotThrowAny {
                    HashedPassword.create(password)
                }
            }
        }

        this.describe("doesMatch") {
            it("パスワードが一致していたらtrueを返す") {
                // given
                val password = "password"
                val hashedPassword = HashedPassword.create(password)
                val expected = true

                // when
                val actual = HashedPassword.doesMatch(password, hashedPassword)

                // then
                actual shouldBe expected
            }

            it("パスワードが一致していなかったらfalseを返す") {
                // given
                val password = "password"
                val incorrectPassword = "incorrectPassword"
                val incorrectHashedPassword = HashedPassword.create(incorrectPassword)
                val expected = false

                // when
                val actual = HashedPassword.doesMatch(password, incorrectHashedPassword)

                // then
                actual shouldBe expected
            }
        }
    }
}

package com.example.backendkotlin.domain

import com.example.backendkotlin.infrastructure.db.table.VillageTable.salt
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.Tuple2
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.springframework.security.crypto.bcrypt.BCrypt

/**
 * HashedPasswordWithRandomSaltエンティティのテスト
 */
class HashedPasswordWithRandomSaltUT : DescribeSpec() {

    override suspend fun beforeTest(testCase: TestCase) {
        mockkStatic(BCrypt::class)
    }

    override fun afterTest(f: suspend (Tuple2<TestCase, TestResult>) -> Unit) {
        unmockkStatic(BCrypt::class)
    }

    init {
        this.describe("HashedPasswordWithRandomSalt") {
            context("create") {
                it("照合可能なパスワードを生成できる") {
                    // given
                    val password = "password"
                    val salt = "salt"
                    val hashedPassword = "hashedPassword"

                    // and
                    every { BCrypt.gensalt() } returns salt
                    every { BCrypt.hashpw(password, salt) } returns hashedPassword
                    every { BCrypt.checkpw(password, hashedPassword) } returns true
                    val expected = HashedPasswordWithRandomSalt(password = password, salt = salt, hashedPassword = hashedPassword)

                    // when
                    val actual = HashedPasswordWithRandomSalt.create(password)

                    // then
                    actual shouldBe expected
                }
            }

            context("validation") {
                it("照合不可能なパスワードは生成できない") {
                    // given
                    val password = "password"
                    val salt = "salt"
                    val hashedPassword = "hashedPassword"

                    // and
                    every { BCrypt.checkpw(password, hashedPassword) } returns false

                    // when, then
                    val exception = shouldThrowExactly<IllegalArgumentException> {
                        HashedPasswordWithRandomSalt(password = password, salt = salt, hashedPassword = hashedPassword)
                    }
                    exception.message shouldBe "Failed to verify password"
                }

                it("saltが不正な場合は生成できない") {
                    // given
                    val password = "password"
                    val correctSalt = "correctSalt"
                    val incorrectSalt = "incorrectSalt"
                    val hashedPassword = "hashedPassword"
                    val incorrectHashedPassword = "incorrectHashedPassword"

                    // and
                    every { BCrypt.checkpw(password, hashedPassword) } returns true
                    every { BCrypt.hashpw(password, correctSalt) } returns hashedPassword
                    every { BCrypt.hashpw(password, incorrectSalt) } returns incorrectHashedPassword

                    // when, then
                    val exception = shouldThrowExactly<IllegalArgumentException> {
                        HashedPasswordWithRandomSalt(password = password, salt = incorrectSalt, hashedPassword = hashedPassword)
                    }
                    exception.message shouldBe "Please input correct salt"
                }
            }
        }
    }
}

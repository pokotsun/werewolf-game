package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.infrastructure.db.UserRepositoryImpl
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.springframework.boot.test.context.SpringBootTest

/**
 * UserRepositoryImplの結合テスト
 *
 * TestContainerを利用してDBの接続を行い、UserRepositoryImplのテストを行う
 */
@SpringBootTest
class UserRepositoryImplIT(
    private val userRepository: UserRepositoryImpl,
) : DescribeSpecUsingPostgreSQLTestContainer() {
    init {
        this.describe("createUser") {
            context("正常系") {
                it("ユーザが作成される") {
                    // given
                    val user = User(
                        id = UserId.generate(),
                        name = "ユーザ1",
                        isActive = true,
                    )

                    // when
                    val createdUser = userRepository.createUser(user)

                    // then
                    createdUser shouldBe user
                }
            }
            context("異常系") {
                it("同じIDのユーザーをリクエストすると例外が発生する") {
                    // given
                    val sameUserId = UserId.generate()
                    val user1 = User(
                        id = sameUserId,
                        name = "ユーザ1",
                        isActive = true,
                    )
                    val user2 = User(
                        id = sameUserId,
                        name = "ユーザ2",
                        isActive = true,
                    )

                    // when
                    shouldNotThrowAny { userRepository.createUser(user1) }
                    shouldThrow<ExposedSQLException> {
                        userRepository.createUser(user2)
                    }
                }
            }
        }
    }
}

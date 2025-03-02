package com.example.backendkotlin.infrastracture.db

import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.infrastructure.db.UserRepositoryImpl
import com.example.backendkotlin.util.KSelect
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.instancio.Instancio
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
                    val user = Instancio.create(User::class.java)
                    val hashedPassword = Instancio.create(HashedPassword::class.java)

                    // when
                    val createdUser = userRepository.createUser(user, hashedPassword)

                    // then
                    createdUser shouldBe user
                }
            }
            context("異常系") {
                it("同じIDのユーザーをリクエストすると例外が発生する") {
                    // given
                    val sameUserId = Instancio.create(UserId::class.java)
                    val user1 = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), sameUserId)
                        .set(KSelect.field(User::isActive), true)
                        .create()
                    val user1HashedPassword = Instancio.create(HashedPassword::class.java)
                    val user2 = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), sameUserId)
                        .set(KSelect.field(User::isActive), true)
                        .create()
                    val user2HashedPassword = Instancio.create(HashedPassword::class.java)

                    // when
                    shouldNotThrowAny { userRepository.createUser(user1, user1HashedPassword) }
                    shouldThrow<ExposedSQLException> {
                        userRepository.createUser(user2, user2HashedPassword)
                    }
                }
            }
        }
    }
}

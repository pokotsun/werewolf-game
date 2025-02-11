package com.example.backendkotlin.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.DescribeSpec
import java.util.UUID

/**
 * UserIdエンティティのテスト
 */
class UserIdUT : DescribeSpec() {
    init {
        describe("UserId") {
            it("UUIDの形式の場合はオブジェクトを生成できる") {
                // given
                val id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")

                // when, then
                shouldNotThrowAny {
                    UserId(id)
                }
            }
        }
        describe("generate") {
            it("オブジェクトを生成できる") {
                // when, then
                shouldNotThrowAny {
                    UserId.generate()
                }
            }
        }
    }
}

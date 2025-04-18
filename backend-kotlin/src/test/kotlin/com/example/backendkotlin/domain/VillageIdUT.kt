package com.example.backendkotlin.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import java.util.UUID

/**
 * VillageIdエンティティのテスト
 */
class VillageIdUT : DescribeSpec() {
    init {
        describe("VillageId") {
            it("UUIDの形式の場合はオブジェクトを生成できる") {
                // given
                val id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")

                // when, then
                shouldNotThrowAny {
                    VillageId(id)
                }
            }
        }

        describe("generate()") {
            it("オブジェクトを生成できる") {
                // when, then
                shouldNotThrowAny {
                    VillageId.generate()
                }
            }
        }

        describe("from()") {
            it("UUIDの仕様に沿った文字列からオブジェクトを生成できる") {
                // given
                val value = "123e4567-e89b-12d3-a456-426614174000"

                // when, then
                shouldNotThrowAny {
                    VillageId.from(value)
                }
            }

            it("UUIDの仕様に沿っていない文字列からオブジェクトを生成できない") {
                // given
                val value = "value"

                // when, then
                shouldThrow<IllegalArgumentException> {
                    VillageId.from(value)
                }
            }
        }
    }
}

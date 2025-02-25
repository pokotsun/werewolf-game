package com.example.backendkotlin.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.DescribeSpec

/**
 * HashedPasswordWithRandomSaltエンティティのテスト
 */
class HashedPasswordWithRandomSaltUT : DescribeSpec() {

    init {
        this.describe("HashedPasswordWithRandomSalt") {
            context("create") {
                it("照合可能なパスワードを生成できる") {
                    // then
                    shouldNotThrowAny {
                        HashedPasswordWithRandomSalt.create("password")
                    }
                }
            }
        }
    }
}

package com.example.backendkotlin.config

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.extensions.spring.SpringExtension

/**
 * テストプロジェクト全体の設定をまとめたクラス
 */
object WereWolfGameProjectConfig : AbstractProjectConfig() {
    override fun extensions(): List<Extension> {
        return listOf(SpringExtension, InitializeTestDatabaseExtension)
    }
}

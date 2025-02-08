package com.example.backendkotlin.config

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension

object ProjectConfig : AbstractProjectConfig() {
    override fun extensions(): List<Extension> {
        return listOf(
            InitializeTestDatabase,
        )
    }
}

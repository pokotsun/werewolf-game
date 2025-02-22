package com.example.backendkotlin.config

import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.VillageId
import org.instancio.generator.Generator
import org.instancio.spi.InstancioServiceProvider
import java.util.UUID

class InstancioProvider : InstancioServiceProvider {
    override fun getGeneratorProvider(): InstancioServiceProvider.GeneratorProvider {
        return InstancioServiceProvider.GeneratorProvider { node, _ ->
            when (node.targetClass) {
                UserId::class.java -> Generator { UserId(UUID.randomUUID()) }
                VillageId::class.java -> Generator { VillageId(UUID.randomUUID()) }
                else -> null
            }
        }
    }
}

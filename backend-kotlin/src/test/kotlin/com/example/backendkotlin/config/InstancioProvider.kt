package com.example.backendkotlin.config

import org.instancio.spi.InstancioServiceProvider

class InstancioProvider: InstancioServiceProvider {
    override fun getGeneratorProvider(): InstancioServiceProvider.GeneratorProvider {
        return InstancioServiceProvider.GeneratorProvider { node, _ ->
            when(node.targetClass) {
                else -> null
            }
        }
    }
}

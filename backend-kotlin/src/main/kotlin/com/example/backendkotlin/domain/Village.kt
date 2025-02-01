package com.example.backendkotlin.domain

data class Village(
    val id: VillageId,
    val name: String,
    val citizenCount: Int,
    val werewolfCount: Int,
    val fortuneTellerCount: Int,
    val knightCount: Int,
    val psychicCount: Int,
    val madmanCount: Int,
    val isInitialActionActive: Boolean,
)

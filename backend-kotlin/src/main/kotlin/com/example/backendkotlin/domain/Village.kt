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
) {
    init {
        require(citizenCount >= 0) { "citizenCount must be greater than or equal to 0" }
        require(werewolfCount >= 0) { "werewolfCount must be greater than or equal to 0" }
        require(fortuneTellerCount >= 0) { "fortuneTellerCount must be greater than or equal to 0" }
        require(knightCount >= 0) { "knightCount must be greater than or equal to 0" }
        require(psychicCount >= 0) { "psychicCount must be greater than or equal to 0" }
        require(madmanCount >= 0) { "madmanCount must be greater than or equal to 0" }
    }
}

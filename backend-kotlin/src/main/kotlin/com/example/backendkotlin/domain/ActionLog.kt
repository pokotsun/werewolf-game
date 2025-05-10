package com.example.backendkotlin.domain

data class ActionLog(
    val day: Int,
    val term: GameTerm,
    val targetPlayer: Player,
    val result: ActorTeam?,
)

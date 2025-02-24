package com.example.backendkotlin.domain

/**
 * 人狼ゲームの村を表すエンティティ
 *
 * @param id 村ID
 * @param name 村名
 * @param citizenCount 村人の数
 * @param werewolfCount 人狼の数
 * @param fortuneTellerCount 占い師の数
 * @param knightCount 騎士の数
 * @param psychicCount 霊媒師の数
 * @param madmanCount 狂人の数
 * @param isInitialActionActive 初日のアクションが有効かどうか
 * @param gameMasterUserId この村のゲームマスターのユーザID
 * @param currentUserNumber 現在村に参加しているユーザーの数(ゲームマスターを含む)
 * @param isRecruited 村が募集中かどうか
 */
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
    val gameMasterUserId: UserId,
    val currentUserNumber: Int = 1,
    val isRecruited: Boolean = true,
) {
    val userNumber: Int
        get() = citizenCount + werewolfCount + fortuneTellerCount + knightCount + psychicCount + madmanCount
    init {
        require(citizenCount >= 0) { "citizenCount must be greater than or equal to 0" }
        require(werewolfCount >= 0) { "werewolfCount must be greater than or equal to 0" }
        require(fortuneTellerCount >= 0) { "fortuneTellerCount must be greater than or equal to 0" }
        require(knightCount >= 0) { "knightCount must be greater than or equal to 0" }
        require(psychicCount >= 0) { "psychicCount must be greater than or equal to 0" }
        require(madmanCount >= 0) { "madmanCount must be greater than or equal to 0" }
        require(userNumber >= 0) { "user_number must be greater than or equal to 0" }
        require(currentUserNumber >= 0) { "currentUserNumber must be greater than or equal to 0" }
    }
}

package com.example.backendkotlin.domain

/**
 * プレイヤーの役職を表す列挙型
 *
 * @param id 役職ID
 * @param surfaceTeam 表向きの陣営(勝敗条件や占い師の結果などに参照される陣営）
 * @param realTeam 実際の陣営(勝利した時にポイント付与で参照する陣営）
 */
enum class Actor(val id: Int, val surfaceTeam: ActorTeam, val realTeam: ActorTeam) {
    WEREWOLF(1, ActorTeam.WEREWOLF, ActorTeam.WEREWOLF),
    CITIZEN(2, ActorTeam.CITIZEN, ActorTeam.CITIZEN),
    FORTUNE_TELLER(3, ActorTeam.CITIZEN, ActorTeam.CITIZEN),
    KNIGHT(4, ActorTeam.CITIZEN, ActorTeam.CITIZEN),
    PSYCHIC(5, ActorTeam.CITIZEN, ActorTeam.CITIZEN),
    MADMAN(6, ActorTeam.CITIZEN, ActorTeam.WEREWOLF),
    ;

    companion object {
        /**
         * ある村の役職リストをシャッフルした状態で作成する
         *
         * @param village 村
         *
         * @return シャッフルされた役職リスト
         */
        fun createShuffledActorList(village: Village): List<Actor> {
            val werewolves = List(size = village.werewolfCount) { WEREWOLF }
            val citizens = List(size = village.citizenCount) { CITIZEN }
            val fortuneTellers = List(size = village.fortuneTellerCount) { FORTUNE_TELLER }
            val knights = List(size = village.knightCount) { KNIGHT }
            val psychics = List(size = village.psychicCount) { PSYCHIC }
            val madmen = List(size = village.madmanCount) { MADMAN }
            val actors = werewolves + citizens + fortuneTellers + knights + psychics + madmen
            return actors.shuffled()
        }
    }
}

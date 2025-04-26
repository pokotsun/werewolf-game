package com.example.backendkotlin.domain

/**
 * プレイヤーの役職を表す列挙型
 *
 * @param id プレイヤーID
 * @param user ユーザー情報
 * @param actor 役職
 * @param isDead 生存しているかどうか
 */
data class Player(
    val id: PlayerId,
    val user: User,
    val actor: Actor,
    val isDead: Boolean,
) {
    companion object {
        /**
         * プレイヤーを作成する
         *
         * @param users ユーザーリスト
         * @param shuffledActorList シャッフルされた役職リスト
         *
         * @condition
         * ユーザーリストと役職リストのサイズが一致すること。
         * 事前に村の募集人数に必要な数のユーザーが参加していることを確認し、
         * 不足している場合はWerewolfErrorCode.LACK_VILLAGE_USERをスローしておく想定
         *
         * @return プレイヤーリスト
         */
        fun createPlayers(users: List<User>, shuffledActorList: List<Actor>): List<Player> {
            // ユーザー数と役職数が一致することを確認
            if (users.size != shuffledActorList.size) {
                throw IllegalArgumentException("ユーザー数と役職数が一致しません")
            }
            val players = users.mapIndexed { index, user ->
                val playerId = PlayerId.generate()
                val actor = shuffledActorList[index]
                val isDead = false
                Player(playerId, user, actor, isDead)
            }
            return players
        }
    }
}

package com.example.backendkotlin.domain

/**
 * 村と村のパスワードとその村に参加しているユーザー(パスワード情報つき)の情報を持つクラス
 *
 * @property village 村
 * @property villagePassword 村のパスワード
 * @property userCredentials 村に参加しているユーザー(パスワード情報付き)の情報
 */
data class VillageCredentialWithUserCredentials(
    val village: Village,
    val villagePassword: HashedPassword,
    val userCredentials: List<UserCredential>,
) {
    /**
     * 村に全員参加しているかを確認する関数
     * @return true: 村に全員参加している false: 村に全員参加していない
     */
    fun doesJoinAllUsers(): Boolean {
        val userCount = village.userNumber
        val joinedUsers = userCredentials.size
        return userCount == joinedUsers
    }
}

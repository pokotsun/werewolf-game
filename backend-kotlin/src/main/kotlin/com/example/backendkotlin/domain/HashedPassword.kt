package com.example.backendkotlin.domain

import org.springframework.security.crypto.bcrypt.BCrypt

/**
 * ランダムなsaltとハッシュ化されたパスワードを保持するクラス
 *
 * @property value ハッシュ化されたパスワード
 */
data class HashedPassword(
    val value: String,
) {
    companion object {
        /**
         * パスワードをハッシュ化する
         *
         * @param password パスワード
         * @return ハッシュ化されたパスワード
         */
        fun create(password: String): HashedPassword {
            val salt = BCrypt.gensalt()
            val hashedPassword = BCrypt.hashpw(password, salt)
            return HashedPassword(hashedPassword)
        }

        /**
         * パスワードが一致するかどうかを判定する
         *
         * @param password パスワード
         * @return ハッシュ化されたパスワード
         */
        fun doesMatch(password: String, hashedPassword: HashedPassword): Boolean {
            return BCrypt.checkpw(password, hashedPassword.value)
        }
    }
}

package com.example.backendkotlin.domain

import org.springframework.security.crypto.bcrypt.BCrypt

/**
 * ランダムなsaltとハッシュ化されたパスワードを保持するクラス
 *
 * @param password パスワード
 *
 * @property salt ランダムなsalt
 * @property hashedPassword ハッシュ化されたパスワード
 */
data class HashedPasswordWithRandomSalt(
    val password: String,
    val salt: String,
    val hashedPassword: String,
) {
    init {
        require(BCrypt.checkpw(password, hashedPassword)) { "Failed to verify password" }
        require(hashedPassword == BCrypt.hashpw(password, salt)) { "Please input correct salt" }
    }

    companion object {
        fun create(password: String): HashedPasswordWithRandomSalt {
            val salt = BCrypt.gensalt()
            val hashedPassword = BCrypt.hashpw(password, salt)
            return HashedPasswordWithRandomSalt(password, salt, hashedPassword)
        }
    }
}

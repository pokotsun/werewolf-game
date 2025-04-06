package com.example.backendkotlin.domain

/**
 * ユーザーとそのパスワード情報を持つクラス
 *
 * @property user ユーザー
 * @property userPassword ユーザーのパスワード
 */
data class UserCredential(
    val user: User,
    val userPassword: HashedPassword,
)

package com.example.backendkotlin.domain

/**
 * Userに関するRepository
 */
interface UserRepository {
    /**
     * ユーザを作成する
     *
     * @param user ユーザ
     *
     * @return 作成したユーザ
     */
    fun createUser(user: User, hashedPassword: HashedPassword): User
}

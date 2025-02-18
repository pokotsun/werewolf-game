package com.example.backendkotlin.infrastracture.db.record

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.infrastructure.db.table.UserTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Userテーブルのレコードを表すクラス
 *
 * @property id ユーザID
 * @property name ユーザ名
 * @property isActive アクティブかどうか
 */
data class UserRecord(
    val id: UserId,
    val name: String,
    val isActive: Boolean,
) {
    /**
     * UserRecordのコンストラクタ
     *
     * @param user ユーザ
     * @return UserRecord
     */
    constructor(user: User) : this(
        id = user.id,
        name = user.name,
        isActive = user.isActive,
    )

    /**
     * UserTableにレコードを追加する
     *
     * @return UserRecord
     */
    fun insert(): UserRecord {
        transaction {
            UserTable.insert {
                it[UserTable.id] = this@UserRecord.id.value
                it[name] = this@UserRecord.name
                it[isActive] = this@UserRecord.isActive
            }
        }
        return this
    }
}

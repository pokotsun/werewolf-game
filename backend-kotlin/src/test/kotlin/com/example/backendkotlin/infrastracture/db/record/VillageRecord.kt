package com.example.backendkotlin.infrastracture.db.record

import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.infrastructure.db.table.VillageTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Villageテーブルのレコードを表すクラス
 *
 * @property id 村ID
 * @property name 村名
 * @property salt パスワードのソルト
 * @property passwordHash パスワードのハッシュ値
 * @property citizenCount 村人の数
 * @property werewolfCount 人狼の数
 * @property fortuneTellerCount 占い師の数
 * @property knightCount 騎士の数
 * @property psychicCount 霊能者の数
 * @property madmanCount 狂人の数
 * @property isInitialActionActive 初日のアクションが有効かどうか
 * @property gameMasterUserId GMのユーザID
 */
data class VillageRecord(
    val id: VillageId,
    val name: String,
    val passwordHash: String,
    val citizenCount: Int,
    val werewolfCount: Int,
    val fortuneTellerCount: Int,
    val knightCount: Int,
    val psychicCount: Int,
    val madmanCount: Int,
    val isInitialActionActive: Boolean,
    val gameMasterUserId: UserId,
) {
    /**
     * VillageRecordのコンストラクタ
     *
     * @param village 村
     * @param hashedPassword パスワードのハッシュ値
     */
    constructor(village: Village, hashedPassword: HashedPassword) : this(
        id = village.id,
        name = village.name,
        passwordHash = hashedPassword.value,
        citizenCount = village.citizenCount,
        werewolfCount = village.werewolfCount,
        fortuneTellerCount = village.fortuneTellerCount,
        knightCount = village.knightCount,
        psychicCount = village.psychicCount,
        madmanCount = village.madmanCount,
        isInitialActionActive = village.isInitialActionActive,
        gameMasterUserId = village.gameMasterUserId,
    )

    /**
     * テストクラス内でVillageテーブルにレコードを追加するUtilメソッド
     *
     * @return VillageRecord
     */
    fun insert(): VillageRecord {
        transaction {
            VillageTable.insert {
                it[id] = this@VillageRecord.id.value
                it[name] = this@VillageRecord.name
                it[passwordHash] = this@VillageRecord.passwordHash
                it[citizenCount] = this@VillageRecord.citizenCount
                it[werewolfCount] = this@VillageRecord.werewolfCount
                it[fortuneTellerCount] = this@VillageRecord.fortuneTellerCount
                it[knightCount] = this@VillageRecord.knightCount
                it[psychicCount] = this@VillageRecord.psychicCount
                it[madmanCount] = this@VillageRecord.madmanCount
                it[isInitialActionActive] = this@VillageRecord.isInitialActionActive
                it[gameMasterUserId] = this@VillageRecord.gameMasterUserId.value
            }
        }
        return this
    }
}

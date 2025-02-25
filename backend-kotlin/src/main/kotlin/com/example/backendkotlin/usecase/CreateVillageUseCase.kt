package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.HashedPasswordWithRandomSalt
import com.example.backendkotlin.domain.RUserVillageRepository
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.UserRepository
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *　村を作成するユースケース
 *
 * @param userRepository ユーザーリポジトリ
 * @param villageRepository 村リポジトリ
 * @param rUserVillageRepository ユーザー村リポジトリ
 */
@Service
class CreateVillageUseCase(
    private val userRepository: UserRepository,
    private val villageRepository: VillageRepository,
    private val rUserVillageRepository: RUserVillageRepository,
) {
    /**
     * 村を作成する
     * ユーザーから受け付けたパスワードを暗号化して保存する
     *
     * @param gameMasterName ゲームマスター名
     * @param villageName 村名
     * @param villageCitizenCount 村の市民の数
     * @param villageWerewolfCount 村の人狼の数
     * @param villageFortuneTellerCount 村の占い師の数
     * @param villageKnightCount 村の騎士の数
     * @param villagePsychicCount 村の霊能者の数
     * @param villageMadmanCount 村の狂人の数
     * @param villageIsInitialActionActive 村の初日の行動が有効かどうか
     * @param villagePassword 村のパスワード
     *
     * @return 作成した村
     */
    @Transactional
    fun invoke(
        gameMasterName: String,
        villageName: String,
        villageCitizenCount: Int,
        villageWerewolfCount: Int,
        villageFortuneTellerCount: Int,
        villageKnightCount: Int,
        villagePsychicCount: Int,
        villageMadmanCount: Int,
        villageIsInitialActionActive: Boolean,
        villagePassword: String,
    ): Village {
        // ゲームマスターを作成
        // ドメインオブジェクトの作成
        val newGameMasterId = UserId.generate()
        val newGameMaster = User(
            id = newGameMasterId,
            name = gameMasterName,
            isActive = true,
        )
        // DBに保存
        val createdGameMaster = userRepository.createUser(newGameMaster)

        // 村の作成
        val newVillageId = VillageId.generate()
        val newVillage = Village(
            id = newVillageId,
            name = villageName,
            citizenCount = villageCitizenCount,
            werewolfCount = villageWerewolfCount,
            fortuneTellerCount = villageFortuneTellerCount,
            knightCount = villageKnightCount,
            psychicCount = villagePsychicCount,
            madmanCount = villageMadmanCount,
            isInitialActionActive = villageIsInitialActionActive,
            gameMasterUserId = createdGameMaster.id,
        )
        // パスワードの暗号化
        val passwordWithRandomSalt = HashedPasswordWithRandomSalt.create(villagePassword)

        // DBに保存
        val createdVillage = villageRepository.createVillage(
            village = newVillage,
            hashedPasswordWithRandomSalt = passwordWithRandomSalt,
        )

        // ユーザーと村の紐付け
        rUserVillageRepository.save(createdGameMaster.id, createdVillage.id)
        return createdVillage
    }
}

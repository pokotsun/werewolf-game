package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.RUserVillageRepository
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserRepository
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageRepository
import org.springframework.security.crypto.bcrypt.BCrypt
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
     * @param village 村
     * @param password パスワード
     *
     * @return 作成した村
     */
    @Transactional
    fun invoke(village: Village, password: String, gameMaster: User): Village {
        // ゲームマスターを作成
        val createdGameMaster = userRepository.createUser(gameMaster)
        if (createdGameMaster.id != village.gameMasterUserId) {
            throw RuntimeException("Game master has created, but id is not matched")
        }

        // 村の作成
        // パスワードの暗号化
        val salt = BCrypt.gensalt()
        val passwordHash = BCrypt.hashpw(password, salt)
        // パスワード検証
        if (!BCrypt.checkpw(password, passwordHash)) {
            throw RuntimeException("Password encryption failed")
        }
        val createdVillage = villageRepository.createVillage(village, passwordHash, salt)

        // ユーザーと村の紐付け
        rUserVillageRepository.save(createdGameMaster.id, createdVillage.id)
        return createdVillage
    }
}

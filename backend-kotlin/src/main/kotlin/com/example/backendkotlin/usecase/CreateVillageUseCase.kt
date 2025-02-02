package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageRepository
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class CreateVillageUseCase(
    private val villageRepository: VillageRepository,
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
    fun invoke(village: Village, password: String): Village {
        val salt = BCrypt.gensalt()
        val passwordHash = BCrypt.hashpw(password, salt)
        // パスワード検証
        if (!BCrypt.checkpw(password, passwordHash)) {
            throw RuntimeException("Password encryption failed")
        }
        return villageRepository.createVillage(village, passwordHash, salt)
    }
}

package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.RUserVillageRepository
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.UserRepository
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.infrastructure.db.table.RUserVillageTable.userId
import org.springframework.stereotype.Service
import java.util.UUID

/**
 *  一般ユーザーが村にログインするユースケース
 */
@Service
class EnterVillageUseCase(
    private val villageRepository: VillageRepository,
    private val userRepository: UserRepository,
    private val rUserVillageRepository: RUserVillageRepository,
) {

    /**
     * 一般ユーザーが村にログインする
     *
     * @param villageIdString 村ID
     * @param villagePassword 村パスワード
     * @param userName ユーザー名
     * @param userPassword ユーザーパスワード
     *
     * @return 村IDとユーザーID
     */
    fun invoke(villageIdString: String, villagePassword: String, userName: String, userPassword: String): Pair<VillageId, UserId> {
        // リクエストされた村が存在するか確認
        val villageId = VillageId(UUID.fromString(villageIdString))
        val villageAndHashedPassword = villageRepository.selectVillageById(villageId) ?: throw IllegalArgumentException("村が存在しません")

        // 取得した村のパスワードが正しいか確認
        if (HashedPassword.doesMatch(villagePassword, villageAndHashedPassword.second)) {
            throw IllegalArgumentException("パスワードが違います")
        }

        // ユーザーを作成
        val userId = UserId.generate()
        val newUser = User(userId, userName, true)

        // ユーザーのパスワードを暗号化
        val userHashedPassword = HashedPassword.create(userPassword)

        // ユーザーを保存
        userRepository.createUser(newUser, userHashedPassword)

        // ユーザーと村を紐付ける
        rUserVillageRepository.save(userId, villageId)

        // レスポンスを作成
        return Pair(villageId, userId)
    }
}

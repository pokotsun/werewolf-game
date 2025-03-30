package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.domain.WerewolfErrorCode
import com.example.backendkotlin.domain.WerewolfException
import org.springframework.stereotype.Service

/**
 * 村に参加しているユーザーを取得するユースケース
 */
@Service
class GetCurrentVillageUsersUseCase(
    private val villageRepository: VillageRepository,
) {
    /**
     * 村に参加しているユーザーを取得する
     *
     * @param villageIdString 村ID
     * @param villagePassword 村パスワード
     * @param userIdString ユーザーID
     * @param userIdPassword ユーザーパスワード
     *
     * @return 村情報と参加しているユーザーのリスト
     */
    fun invoke(
        villageIdString: String,
        villagePassword: String,
        userIdString: String,
        userIdPassword: String,
    ): Pair<Village, List<User>> {
        // 村とその村に参加しているユーザーを取得
        val villageId = VillageId.generate(villageIdString)
        val (village, villageHashedPassword, userWithHashedPasswordList) = villageRepository.selectVillageWithCurrentUsersById(
            villageId = villageId,
        ) ?: throw WerewolfException(WerewolfErrorCode.RESOURCE_NOT_FOUND, "村が存在しません")

        // 村パスワードが正しいかチェック
        if (!HashedPassword.doesMatch(villagePassword, villageHashedPassword)) {
            throw WerewolfException(WerewolfErrorCode.VILLAGE_PASSWORD_IS_WRONG, "村のパスワードが違います")
        }

        // ユーザーIDとパスワードが正しいかチェック
        val userId = UserId.from(userIdString)
        val (_, requestUserHashedPassword) = userWithHashedPasswordList.find { it.first.id == userId }
            ?: throw WerewolfException(WerewolfErrorCode.RESOURCE_NOT_FOUND, "ユーザーが存在しません")
        if (!HashedPassword.doesMatch(userIdPassword, requestUserHashedPassword)) {
            throw WerewolfException(WerewolfErrorCode.USER_PASSWORD_IS_WRONG, "ユーザーパスワードが違います")
        }

        // レスポンスを返却
        val users = userWithHashedPasswordList.map { it.first }
        return Pair(village, users)
    }
}

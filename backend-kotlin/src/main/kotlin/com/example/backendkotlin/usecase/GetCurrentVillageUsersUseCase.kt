package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageId
import org.springframework.stereotype.Service

/**
 * 村に参加しているユーザーを取得するユースケース
 */
@Service
class GetCurrentVillageUsersUseCase {
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
        // Todo: 村に参加しているユーザーを取得する処理を実装する
        val village = Village(
            id = VillageId.generate(),
            name = "村名",
            citizenCount = 10,
            werewolfCount = 2,
            fortuneTellerCount = 1,
            knightCount = 1,
            psychicCount = 1,
            madmanCount = 1,
            isInitialActionActive = true,
            gameMasterUserId = UserId.generate(),
            currentUserNumber = 1,
            isRecruited = true,
        )
        val users = listOf(
            User(
                id = UserId.generate(),
                name = "ユーザー1",
                isActive = true,
            ),
        )
        return Pair(village, users)
    }
}

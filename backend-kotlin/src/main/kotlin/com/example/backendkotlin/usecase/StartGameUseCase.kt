package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.VillageId
import org.springframework.stereotype.Service

/**
 *  ある村のゲームを開始するユースケース
 *
 *  @param villageIdString 村ID
 *  @param villagePassword 村パスワード
 *  @param gameMasterIdString ゲームマスターID
 *  @param gameMasterPassword ゲームマスターパスワード
 *
 *  @return 村IDとユーザーリスト
 */
@Service
class StartGameUseCase {
    fun invoke(villageIdString: String, villagePassword: String, gameMasterIdString: String, gameMasterPassword: String): Pair<VillageId, List<User>> {
        // Todo: 村を抽出

        // Todo: 村のパスワードを確認

        // Todo: ゲームマスターを抽出

        // Todo: ゲームマスターのパスワードを確認

        // Todo: ゲームを開始

        // Todo: レスポンスを作成
        val villageId = VillageId.generate()
        val users = listOf(User(UserId.generate(), "user1", true))
        return Pair(villageId, users)
    }
}

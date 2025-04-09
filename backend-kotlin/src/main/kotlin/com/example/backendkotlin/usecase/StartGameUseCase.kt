package com.example.backendkotlin.usecase

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
    fun invoke(villageIdString: String, villagePassword: String, gameMasterIdString: String, gameMasterPassword: String): Pair<String, List<Pair<String, String>>> {
        // Todo: 村を抽出

        // Todo: 村のパスワードを確認

        // Todo: ゲームマスターを抽出

        // Todo: ゲームマスターのパスワードを確認

        // Todo: ゲームを開始
        val gameId = "gameId" // 仮のゲームID

        // Todo: プレイヤー一覧取得
        val players = listOf(
            Pair("player1_id", "player1_name"),
            Pair("player2_id", "player2_name"),
        )

        // Todo: レスポンスを作成
        return Pair(gameId, players)
    }
}

package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.Game
import com.example.backendkotlin.domain.GameId
import com.example.backendkotlin.domain.GameTerm
import org.springframework.stereotype.Service

/**
 * ゲームの進行状況を取得するユースケース
 */
@Service
class GetGameStatusUseCase {
    /**
     * ゲームの進行状況を取得する
     *
     * @param gameIdString ゲームID
     * @param userIdString ユーザーID
     * @param userPassword ユーザーパスワード
     * @return ゲームの進行状況
     */
    fun invoke(
        gameIdString: String,
        userIdString: String,
        userPassword: String,
    ): Game {
        // TODO: 実際の実装
        val game = Game(
            id = GameId.from(gameIdString),
            isPlaying = true,
            isInitialActionActive = true,
            day = 1,
            term = GameTerm.NIGHT,
        )
        return game
    }
}

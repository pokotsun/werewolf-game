package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.ActorTeam
import org.springframework.stereotype.Service

/**
 * ゲームの結果を取得するユースケース
 */
@Service
class GetGameResultUseCase {
    /**
     * ゲームの結果を取得する
     *
     * @param gameIdString ゲームID
     * @param userIdString ユーザーID
     * @param userPassword ユーザーパスワード
     * @return ゲームの結果（0: 市民陣営の勝利、1: 人狼陣営の勝利）
     */
    fun invoke(
        gameIdString: String,
        userIdString: String,
        userPassword: String,
    ): ActorTeam? {
        // TODO: 実際の実装
        return ActorTeam.CITIZEN
    }
} 

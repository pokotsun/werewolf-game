package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.Actor
import org.springframework.stereotype.Service

/**
 * プレイヤーの役職を取得するユースケース
 */
@Service
class GetActorUseCase {
    /**
     * プレイヤーの役職を取得する
     *
     * @param gameIdString ゲームID
     * @param userIdString ユーザーID
     * @param userPassword ユーザーパスワード
     * @return プレイヤーの役職
     */
    fun invoke(
        gameIdString: String,
        userIdString: String,
        userPassword: String,
    ): Actor {
        // TODO: 実際の実装
        return Actor.CITIZEN
    }
}

package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.ActorTeam
import org.springframework.stereotype.Service

/**
 * アクションを実行するユースケース
 */
@Service
class ExecSelfActionUseCase {
    /**
     * アクションを実行する
     *
     * @param gameIdString ゲームID
     * @param userIdString ユーザーID
     * @param userPassword ユーザーパスワード
     * @param targetPlayerIdString 対象プレイヤーID
     * @return アクションの実行結果
     */
    fun invoke(
        gameIdString: String,
        userIdString: String,
        userPassword: String,
        targetPlayerIdString: String,
    ): ActorTeam? {
        // TODO: 実際の実装
        return ActorTeam.CITIZEN
    }
} 

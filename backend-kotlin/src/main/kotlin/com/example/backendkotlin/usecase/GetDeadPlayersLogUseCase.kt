package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.GameTerm
import org.springframework.stereotype.Service

/**
 * 死亡したプレイヤーのログを取得するユースケース
 */
@Service
class GetDeadPlayersLogUseCase {
    /**
     * 死亡したプレイヤーのログを取得する
     *
     * @param gameIdString ゲームID
     * @param userIdString ユーザーID
     * @param userPassword ユーザーパスワード
     * @return 死亡したプレイヤーのログ一覧
     */
    fun invoke(
        gameIdString: String,
        userIdString: String,
        userPassword: String,
    ): List<Triple<Int, GameTerm, String>> {
        // TODO: 実際の実装
        return listOf(
            Triple(1, GameTerm.NIGHT, "Taro"),
            Triple(2, GameTerm.DAY, "Hanako"),
        )
    }
} 

package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.ActionLog
import com.example.backendkotlin.domain.Actor
import com.example.backendkotlin.domain.Player
import com.example.backendkotlin.domain.PlayerId
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserId
import org.springframework.stereotype.Service

/**
 * 自分のアクションログを取得するユースケース
 */
@Service
class GetSelfActionLogUseCase {
    /**
     * 自分のアクションログを取得する
     *
     * @param gameIdString ゲームID
     * @param userIdString ユーザーID
     * @param userPassword ユーザーパスワード
     * @return 自分のアクションログ一覧
     */
    fun invoke(
        gameIdString: String,
        userIdString: String,
        userPassword: String,
    ): List<ActionLog> {
        // TODO: 実際の実装
        return listOf(
            ActionLog(
                day = 1,
                term = com.example.backendkotlin.domain.GameTerm.NIGHT,
                targetPlayer = Player(
                    id = PlayerId.generate(),
                    user = User(
                        id = UserId.generate(),
                        name = "Taro",
                        isActive = true,
                    ),
                    actor = Actor.CITIZEN,
                    isDead = false,
                ),
                result = null,
            ),
        )
    }
} 

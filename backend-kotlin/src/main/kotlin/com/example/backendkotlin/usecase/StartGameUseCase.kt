package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.Actor
import com.example.backendkotlin.domain.Game
import com.example.backendkotlin.domain.GameId
import com.example.backendkotlin.domain.GameRepository
import com.example.backendkotlin.domain.GameTerm
import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.Player
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.domain.WerewolfErrorCode
import com.example.backendkotlin.domain.WerewolfException
import org.springframework.stereotype.Service

/**
 *  ある村のゲームを開始するユースケース
 *  @param villageRepository 村リポジトリ
 *  @param gameRepository ゲームリポジトリ
 *
 *  @return 村IDとユーザーリスト
 */
@Service
class StartGameUseCase(
    private val villageRepository: VillageRepository,
    private val gameRepository: GameRepository,
) {
    /**
     * ゲームを開始する
     *
     * @param villageIdString 村ID
     * @param villagePassword 村のパスワード
     * @param gameMasterIdString ゲームマスターID
     * @param gameMasterPassword ゲームマスターのパスワード
     * @return 村IDとユーザーリスト
     */
    fun invoke(villageIdString: String, villagePassword: String, gameMasterIdString: String, gameMasterPassword: String): Pair<GameId, List<Player>> {
        // 対象の村と参加しているユーザーを取得
        val villageId = VillageId.from(villageIdString)
        val villageCredentialWithUserCredentials = villageRepository.selectVillageWithCurrentUsersById(villageId) ?: throw WerewolfException(WerewolfErrorCode.RESOURCE_NOT_FOUND, "村が存在しません")

        // 村のパスワードを確認
        if (!HashedPassword.doesMatch(villagePassword, villageCredentialWithUserCredentials.villagePassword)) {
            throw WerewolfException(WerewolfErrorCode.VILLAGE_PASSWORD_IS_WRONG, "村のパスワードが間違っています")
        }

        // ゲームマスターのパスワードを取得
        val gameMasterId = UserId.from(gameMasterIdString)
        val gameMasterCredential = villageCredentialWithUserCredentials.userCredentials.find { it.user.id == gameMasterId }
            ?: throw WerewolfException(WerewolfErrorCode.RESOURCE_NOT_FOUND, "ゲームマスターが存在しません")

        // ゲームマスターのパスワードを確認
        if (!HashedPassword.doesMatch(gameMasterPassword, gameMasterCredential.userPassword)) {
            throw WerewolfException(WerewolfErrorCode.USER_PASSWORD_IS_WRONG, "ゲームマスターのパスワードが間違っています")
        }

        // 村の募集人数と同じ数のユーザーが村に参加しているかを確認
        if (!villageCredentialWithUserCredentials.doesJoinAllUsers()) {
            throw WerewolfException(WerewolfErrorCode.LACK_VILLAGE_USER, "ゲームを開始するのに必要なユーザーが不足しています")
        }

        // プレイヤーの役職を決める
        val shuffledActorList = Actor.createShuffledActorList(villageCredentialWithUserCredentials.village)
        val players = Player.createPlayers(villageCredentialWithUserCredentials.userCredentials.map { it.user }, shuffledActorList)

        // ゲームを作成
        val gameId = GameId.generate()
        val game = Game(
            gameId,
            true,
            villageCredentialWithUserCredentials.village.isInitialActionActive,
            1,
            GameTerm.NIGHT,
        )

        // ゲームをDBに保存
        val createdGame = gameRepository.createGame(villageId, game, players)

        return Pair(createdGame.id, players)
    }
}

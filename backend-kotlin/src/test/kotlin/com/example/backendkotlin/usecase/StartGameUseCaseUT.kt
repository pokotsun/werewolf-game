package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.Actor
import com.example.backendkotlin.domain.Game
import com.example.backendkotlin.domain.GameId
import com.example.backendkotlin.domain.GameRepository
import com.example.backendkotlin.domain.GameTerm
import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.Player
import com.example.backendkotlin.domain.PlayerId
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserCredential
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageCredentialWithUserCredentials
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
import com.example.backendkotlin.domain.WerewolfErrorCode
import com.example.backendkotlin.domain.WerewolfException
import com.example.backendkotlin.util.KSelect
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.instancio.Instancio
import kotlin.jvm.java

/**
 * StartGameUseCaseのテストクラス
 */
class StartGameUseCaseUT(
    @MockkBean
    private val villageRepository: VillageRepository,
    @MockkBean
    private val gameRepository: GameRepository,
) : DescribeSpec() {
    @InjectMockKs
    private lateinit var target: StartGameUseCase

    override suspend fun beforeSpec(spec: Spec) {
        mockkObject(
            UserId,
            VillageId,
            HashedPassword,
            GameId,
            Actor,
            Player,
        )
    }

    override suspend fun afterSpec(spec: Spec) {
        unmockkObject(
            UserId,
            VillageId,
            HashedPassword,
            GameId,
            Actor,
            Player,
        )
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        // テスト後にMockの挙動を初期化する
        confirmVerified(
            villageRepository,
            gameRepository,
        )
        clearAllMocks()
    }

    init {
        this.describe("invokeメソッドのテスト") {
            context("正常系") {
                it("ゲームを開始できる") {
                    // given
                    val villageId = Instancio.create(VillageId::class.java)
                    val villagePassword = "villagePassword"
                    val gameMasterId = Instancio.create(UserId::class.java)
                    val gameMasterPassword = "gameMasterPassword"
                    val expectedActorList = listOf(Actor.WEREWOLF, Actor.CITIZEN, Actor.CITIZEN, Actor.CITIZEN)
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .set(KSelect.field(Village::gameMasterUserId), gameMasterId)
                        .set(KSelect.field(Village::werewolfCount), 1)
                        .set(KSelect.field(Village::citizenCount), 3)
                        .set(KSelect.field(Village::fortuneTellerCount), 0)
                        .set(KSelect.field(Village::knightCount), 0)
                        .set(KSelect.field(Village::psychicCount), 0)
                        .set(KSelect.field(Village::madmanCount), 0)
                        .create()

                    // 　参加するユーザーの数は4
                    val userCredential1 = Instancio.create(UserCredential::class.java)
                    val userCredential2 = Instancio.create(UserCredential::class.java)
                    val userCredential3 = Instancio.create(UserCredential::class.java)
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), gameMasterId)
                        .create()
                    val gameMasterHashedPassword = Instancio.create(HashedPassword::class.java)
                    val gameMasterCredential = Instancio.of(UserCredential::class.java)
                        .set(KSelect.field(UserCredential::user), gameMaster)
                        .set(KSelect.field(UserCredential::userPassword), gameMasterHashedPassword)
                        .create()
                    val villageHashedPassword = Instancio.create(HashedPassword::class.java)
                    val villageCredentialWithUserCredentials = VillageCredentialWithUserCredentials(
                        village,
                        villageHashedPassword,
                        listOf(userCredential1, userCredential2, userCredential3, gameMasterCredential),
                    )
                    val gameId = Instancio.create(GameId::class.java)
                    val game = Instancio.of(Game::class.java)
                        .set(KSelect.field(Game::id), gameId)
                        .set(KSelect.field(Game::isPlaying), true)
                        .set(KSelect.field(Game::isInitialActionActive), village.isInitialActionActive)
                        .set(KSelect.field(Game::day), 1)
                        .set(KSelect.field(Game::term), GameTerm.NIGHT)
                        .create()
                    val playerIds = listOf(
                        Instancio.create(PlayerId::class.java),
                        Instancio.create(PlayerId::class.java),
                        Instancio.create(PlayerId::class.java),
                        Instancio.create(PlayerId::class.java),
                    )
                    val users = listOf(
                        userCredential1.user,
                        userCredential2.user,
                        userCredential3.user,
                        gameMaster,
                    )
                    val players = listOf(
                        Player(playerIds[0], userCredential1.user, expectedActorList[0], false),
                        Player(playerIds[1], userCredential2.user, expectedActorList[1], false),
                        Player(playerIds[2], userCredential3.user, expectedActorList[2], false),
                        Player(playerIds[3], gameMaster, expectedActorList[3], false),
                    )

                    // and
                    every { VillageId.from(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageWithCurrentUsersById(villageId) } returns villageCredentialWithUserCredentials
                    every { HashedPassword.doesMatch(villagePassword, villageHashedPassword) } returns true
                    every { HashedPassword.doesMatch(gameMasterPassword, gameMasterHashedPassword) } returns true
                    every { GameId.generate() } returns gameId
                    every { Actor.createShuffledActorList(village) } returns expectedActorList
                    every { Player.createPlayers(users, expectedActorList) } returns players
                    every { gameRepository.createGame(villageId, game, players) } returns game

                    // when
                    val result = target.invoke(
                        villageId.value.toString(),
                        villagePassword,
                        gameMasterId.value.toString(),
                        gameMasterPassword,
                    )
                    val actorListResult = result.second.map { it.actor }

                    // then
                    result.first shouldBe gameId
                    actorListResult shouldContainExactlyInAnyOrder expectedActorList
                    verify(exactly = 1) {
                        VillageId.from(villageId.value.toString())
                        villageRepository.selectVillageWithCurrentUsersById(villageId)
                        HashedPassword.doesMatch(villagePassword, villageHashedPassword)
                        HashedPassword.doesMatch(gameMasterPassword, gameMasterHashedPassword)
                        GameId.generate()
                        Actor.createShuffledActorList(village)
                        Player.createPlayers(users, expectedActorList)
                        gameRepository.createGame(villageId, game, players)
                    }
                }
            }

            context("異常系") {
                it("村が存在しない") {
                    // given
                    val villageId = Instancio.create(VillageId::class.java)
                    val villagePassword = "villagePassword"
                    val gameMasterId = Instancio.create(UserId::class.java)
                    val gameMasterPassword = "gameMasterPassword"

                    // and
                    every { VillageId.from(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageWithCurrentUsersById(villageId) } returns null

                    // when
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(
                            villageId.value.toString(),
                            villagePassword,
                            gameMasterId.value.toString(),
                            gameMasterPassword,
                        )
                    }

                    // then
                    exception.code shouldBe WerewolfErrorCode.RESOURCE_NOT_FOUND
                    verify(exactly = 1) {
                        VillageId.from(villageId.value.toString())
                        villageRepository.selectVillageWithCurrentUsersById(villageId)
                    }
                    verify(exactly = 0) {
                        HashedPassword.doesMatch(any(), any())
                        gameRepository.createGame(any(), any(), any())
                    }
                }
                it("村のパスワードが間違っている") {
                    // given
                    val villageId = Instancio.create(VillageId::class.java)
                    val villagePassword = "villagePassword"
                    val gameMasterId = Instancio.create(UserId::class.java)
                    val gameMasterPassword = "gameMasterPassword"
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .create()
                    val villageHashedPassword = Instancio.create(HashedPassword::class.java)
                    val villageCredentialWithUserCredentials = Instancio.of(VillageCredentialWithUserCredentials::class.java)
                        .set(KSelect.field(VillageCredentialWithUserCredentials::village), village)
                        .set(KSelect.field(VillageCredentialWithUserCredentials::villagePassword), villageHashedPassword)
                        .create()

                    // and
                    every { VillageId.from(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageWithCurrentUsersById(villageId) } returns villageCredentialWithUserCredentials
                    every { HashedPassword.doesMatch(villagePassword, villageHashedPassword) } returns false

                    // when
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(
                            villageId.value.toString(),
                            villagePassword,
                            gameMasterId.value.toString(),
                            gameMasterPassword,
                        )
                    }

                    // then
                    exception.code shouldBe WerewolfErrorCode.VILLAGE_PASSWORD_IS_WRONG
                    verify(exactly = 1) {
                        VillageId.from(villageId.value.toString())
                        villageRepository.selectVillageWithCurrentUsersById(villageId)
                        HashedPassword.doesMatch(villagePassword, villageHashedPassword)
                    }
                    verify(exactly = 0) {
                        HashedPassword.doesMatch(gameMasterPassword, any())
                        gameRepository.createGame(any(), any(), any())
                    }
                }
                it("ゲームマスターが存在しない") {
                    // given
                    val villageId = Instancio.create(VillageId::class.java)
                    val villagePassword = "villagePassword"
                    val gameMasterId = Instancio.create(UserId::class.java)
                    val gameMasterPassword = "gameMasterPassword"
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .create()
                    val villageHashedPassword = Instancio.create(HashedPassword::class.java)
                    val villageCredentialWithUserCredentials = Instancio.of(VillageCredentialWithUserCredentials::class.java)
                        .set(KSelect.field(VillageCredentialWithUserCredentials::village), village)
                        .set(KSelect.field(VillageCredentialWithUserCredentials::villagePassword), villageHashedPassword)
                        .set(KSelect.field(VillageCredentialWithUserCredentials::userCredentials), listOf<UserCredential>())
                        .create()

                    // and
                    every { VillageId.from(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageWithCurrentUsersById(villageId) } returns villageCredentialWithUserCredentials
                    every { HashedPassword.doesMatch(villagePassword, villageHashedPassword) } returns true

                    // when
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(
                            villageId.value.toString(),
                            villagePassword,
                            gameMasterId.value.toString(),
                            gameMasterPassword,
                        )
                    }

                    // then
                    exception.code shouldBe WerewolfErrorCode.RESOURCE_NOT_FOUND
                    verify(exactly = 1) {
                        VillageId.from(villageId.value.toString())
                        villageRepository.selectVillageWithCurrentUsersById(villageId)
                        HashedPassword.doesMatch(villagePassword, villageHashedPassword)
                    }
                    verify(exactly = 0) {
                        HashedPassword.doesMatch(gameMasterPassword, any())
                        gameRepository.createGame(any(), any(), any())
                    }
                }
                it("ゲームマスターのパスワードが間違っている") {
                    // given
                    val villageId = Instancio.create(VillageId::class.java)
                    val villagePassword = "villagePassword"
                    val gameMasterId = Instancio.create(UserId::class.java)
                    val gameMasterPassword = "gameMasterPassword"
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .create()
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), gameMasterId)
                        .create()
                    val gameMasterHashedPassword = Instancio.create(HashedPassword::class.java)
                    val gameMasterCredential = Instancio.of(UserCredential::class.java)
                        .set(KSelect.field(UserCredential::user), gameMaster)
                        .set(KSelect.field(UserCredential::userPassword), gameMasterHashedPassword)
                        .create()
                    val villageHashedPassword = Instancio.create(HashedPassword::class.java)
                    val villageCredentialWithUserCredentials = VillageCredentialWithUserCredentials(
                        village,
                        villageHashedPassword,
                        listOf(gameMasterCredential),
                    )

                    // and
                    every { VillageId.from(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageWithCurrentUsersById(villageId) } returns villageCredentialWithUserCredentials
                    every { HashedPassword.doesMatch(villagePassword, villageHashedPassword) } returns true
                    every { HashedPassword.doesMatch(gameMasterPassword, gameMasterHashedPassword) } returns false

                    // when
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(
                            villageId.value.toString(),
                            villagePassword,
                            gameMasterId.value.toString(),
                            gameMasterPassword,
                        )
                    }

                    // then
                    exception.code shouldBe WerewolfErrorCode.USER_PASSWORD_IS_WRONG
                    verify(exactly = 1) {
                        VillageId.from(villageId.value.toString())
                        villageRepository.selectVillageWithCurrentUsersById(villageId)
                        HashedPassword.doesMatch(villagePassword, villageHashedPassword)
                        HashedPassword.doesMatch(gameMasterPassword, gameMasterHashedPassword)
                    }
                }
                it("村の参加人数が足りない") {
                    // given
                    val villageId = Instancio.create(VillageId::class.java)
                    val villagePassword = "villagePassword"
                    val gameMasterId = Instancio.create(UserId::class.java)
                    val gameMasterPassword = "gameMasterPassword"
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .set(KSelect.field(Village::werewolfCount), 2) // ユーザー数以外の数
                        .create()
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), gameMasterId)
                        .create()
                    val gameMasterHashedPassword = Instancio.create(HashedPassword::class.java)
                    val gameMasterCredential = Instancio.of(UserCredential::class.java)
                        .set(KSelect.field(UserCredential::user), gameMaster)
                        .set(KSelect.field(UserCredential::userPassword), gameMasterHashedPassword)
                        .create()
                    val villageHashedPassword = Instancio.create(HashedPassword::class.java)
                    val villageCredentialWithUserCredentials = VillageCredentialWithUserCredentials(
                        village,
                        villageHashedPassword,
                        listOf(gameMasterCredential),
                    )

                    // and
                    every { VillageId.from(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageWithCurrentUsersById(villageId) } returns villageCredentialWithUserCredentials
                    every { HashedPassword.doesMatch(villagePassword, villageHashedPassword) } returns true
                    every { HashedPassword.doesMatch(gameMasterPassword, gameMasterHashedPassword) } returns true

                    // when
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(
                            villageId.value.toString(),
                            villagePassword,
                            gameMasterId.value.toString(),
                            gameMasterPassword,
                        )
                    }

                    // then
                    exception.code shouldBe WerewolfErrorCode.LACK_VILLAGE_USER
                    verify(exactly = 1) {
                        VillageId.from(villageId.value.toString())
                        villageRepository.selectVillageWithCurrentUsersById(villageId)
                        HashedPassword.doesMatch(villagePassword, villageHashedPassword)
                        HashedPassword.doesMatch(gameMasterPassword, gameMasterHashedPassword)
                        villageCredentialWithUserCredentials.doesJoinAllUsers()
                    }
                    verify(exactly = 0) {
                        gameRepository.createGame(any(), any(), any())
                    }
                }
            }
        }
    }
}

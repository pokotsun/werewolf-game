package com.example.backendkotlin.usecase

import com.example.backendkotlin.domain.HashedPassword
import com.example.backendkotlin.domain.User
import com.example.backendkotlin.domain.UserCredential
import com.example.backendkotlin.domain.UserId
import com.example.backendkotlin.domain.Village
import com.example.backendkotlin.domain.VillageCredentialWithUserCredentials
import com.example.backendkotlin.domain.VillageId
import com.example.backendkotlin.domain.VillageRepository
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

/**
 * GetCurrentVillageUsersUseCaseのテストクラス
 */
class GetCurrentVillageUsersUseCaseUT(
    @MockkBean
    private val villageRepository: VillageRepository,
) : DescribeSpec() {
    @InjectMockKs
    private lateinit var target: GetCurrentVillageUsersUseCase

    override suspend fun beforeSpec(spec: Spec) {
        mockkObject(UserId, VillageId, HashedPassword)
    }

    override suspend fun afterSpec(spec: Spec) {
        unmockkObject(UserId, VillageId, HashedPassword)
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        // テスト後にMockの挙動を初期化する
        confirmVerified(
            villageRepository,
        )
        clearAllMocks()
    }

    init {
        this.describe("invoke") {
            it("リクエストされた村の情報とその村に参加しているユーザーのリストを返す") {
                // given
                val gameMasterId = Instancio.create(UserId::class.java)
                val gameMaster = Instancio.of(User::class.java)
                    .set(KSelect.field(User::id), gameMasterId)
                    .create()
                val gameMasterHashedPassword = Instancio.create(HashedPassword::class.java)
                val user1Id = Instancio.create(UserId::class.java)
                val user1 = Instancio.of(User::class.java)
                    .set(KSelect.field(User::id), user1Id)
                    .create()
                val user1Password = "user1-password"
                val user1HashedPassword = Instancio.create(HashedPassword::class.java)
                val villageId = Instancio.create(VillageId::class.java)
                val village = Instancio.of(Village::class.java)
                    .set(KSelect.field(Village::id), villageId)
                    .set(KSelect.field(Village::gameMasterUserId), gameMasterId)
                    .set(KSelect.field(Village::currentUserNumber), 2)
                    .create()
                val villagePassword = "village-password"
                val villageHashedPassword = Instancio.create(HashedPassword::class.java)
                val expected = Pair(village, listOf(gameMaster, user1))

                // and
                every { VillageId.from(villageId.value.toString()) } returns villageId
                every { villageRepository.selectVillageWithCurrentUsersById(villageId) } returns VillageCredentialWithUserCredentials(
                    village,
                    villageHashedPassword,
                    listOf(
                        UserCredential(gameMaster, gameMasterHashedPassword),
                        UserCredential(user1, user1HashedPassword),
                    ),
                )
                every { HashedPassword.doesMatch(villagePassword, villageHashedPassword) } returns true
                every { UserId.from(user1Id.value.toString()) } returns user1Id
                every { HashedPassword.doesMatch(user1Password, user1HashedPassword) } returns true

                // when
                val actual = target.invoke(
                    villageId.value.toString(),
                    villagePassword,
                    user1Id.value.toString(),
                    user1Password,
                )

                // then
                actual.first shouldBe expected.first
                actual.second shouldContainExactlyInAnyOrder expected.second
                verify(exactly = 1) {
                    VillageId.from(villageId.value.toString())
                    villageRepository.selectVillageWithCurrentUsersById(villageId)
                    HashedPassword.doesMatch(villagePassword, villageHashedPassword)
                    UserId.from(user1Id.value.toString())
                    HashedPassword.doesMatch(user1Password, user1HashedPassword)
                }
            }
            context("異常系") {
                it("村が存在しない") {
                    // given
                    val villageId = Instancio.create(VillageId::class.java)

                    // and
                    every { VillageId.from(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageWithCurrentUsersById(villageId) } returns null

                    // when, then
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(villageId.value.toString(), "password", "userName", "password")
                    }
                    exception.message shouldBe "村が存在しません"

                    verify(exactly = 1) {
                        VillageId.from(villageId.value.toString())
                        villageRepository.selectVillageWithCurrentUsersById(villageId)
                    }
                    verify(exactly = 0) {
                        HashedPassword.doesMatch(any(), any())
                        UserId.from(any())
                    }
                }
                it("村のパスワードが間違っている") {
                    // given
                    val gameMasterId = Instancio.create(UserId::class.java)
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), gameMasterId)
                        .create()
                    val gameMasterHashedPassword = Instancio.create(HashedPassword::class.java)
                    val user1Id = Instancio.create(UserId::class.java)
                    val user1 = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), user1Id)
                        .create()
                    val user1Password = "user1-password"
                    val user1HashedPassword = Instancio.create(HashedPassword::class.java)
                    val villageId = Instancio.create(VillageId::class.java)
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .set(KSelect.field(Village::gameMasterUserId), gameMasterId)
                        .set(KSelect.field(Village::currentUserNumber), 2)
                        .create()
                    val villagePassword = "village-password"
                    val villageHashedPassword = Instancio.create(HashedPassword::class.java)

                    // and
                    every { VillageId.from(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageWithCurrentUsersById(villageId) } returns VillageCredentialWithUserCredentials(
                        village,
                        villageHashedPassword,
                        listOf(
                            UserCredential(gameMaster, gameMasterHashedPassword),
                            UserCredential(user1, user1HashedPassword),
                        ),
                    )
                    every { HashedPassword.doesMatch(villagePassword, villageHashedPassword) } returns false

                    // when, then
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(
                            villageId.value.toString(),
                            villagePassword,
                            user1Id.value.toString(),
                            user1Password,
                        )
                    }
                    exception.message shouldBe "村のパスワードが違います"

                    verify(exactly = 1) {
                        VillageId.from(villageId.value.toString())
                        villageRepository.selectVillageWithCurrentUsersById(villageId)
                        HashedPassword.doesMatch(villagePassword, villageHashedPassword)
                    }
                    verify(exactly = 0) {
                        UserId.from(any())
                    }
                }
                it("ユーザーが存在しない") {
                    // given
                    val gameMasterId = Instancio.create(UserId::class.java)
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), gameMasterId)
                        .create()
                    val gameMasterHashedPassword = Instancio.create(HashedPassword::class.java)
                    val user1Id = Instancio.create(UserId::class.java)
                    val user1 = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), user1Id)
                        .create()
                    val user1Password = "user1-password"
                    val user1HashedPassword = Instancio.create(HashedPassword::class.java)
                    val villageId = Instancio.create(VillageId::class.java)
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .set(KSelect.field(Village::gameMasterUserId), gameMasterId)
                        .set(KSelect.field(Village::currentUserNumber), 2)
                        .create()
                    val villagePassword = "village-password"
                    val villageHashedPassword = Instancio.create(HashedPassword::class.java)
                    val notExistUserId = Instancio.create(UserId::class.java)

                    // and
                    every { VillageId.from(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageWithCurrentUsersById(villageId) } returns VillageCredentialWithUserCredentials(
                        village,
                        villageHashedPassword,
                        listOf(
                            UserCredential(gameMaster, gameMasterHashedPassword),
                            UserCredential(user1, user1HashedPassword),
                        ),
                    )
                    every { HashedPassword.doesMatch(villagePassword, villageHashedPassword) } returns true
                    every { UserId.from(notExistUserId.value.toString()) } returns notExistUserId

                    // when, then
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(
                            villageId.value.toString(),
                            villagePassword,
                            notExistUserId.value.toString(),
                            user1Password,
                        )
                    }
                    exception.message shouldBe "ユーザーが存在しません"

                    verify(exactly = 1) {
                        VillageId.from(villageId.value.toString())
                        villageRepository.selectVillageWithCurrentUsersById(villageId)
                        HashedPassword.doesMatch(villagePassword, villageHashedPassword)
                        UserId.from(notExistUserId.value.toString())
                    }
                }
                it("ユーザーのパスワードが間違っている") {
                    // given
                    val gameMasterId = Instancio.create(UserId::class.java)
                    val gameMaster = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), gameMasterId)
                        .create()
                    val gameMasterHashedPassword = Instancio.create(HashedPassword::class.java)
                    val user1Id = Instancio.create(UserId::class.java)
                    val user1 = Instancio.of(User::class.java)
                        .set(KSelect.field(User::id), user1Id)
                        .create()
                    val user1Password = "user1-password"
                    val user1HashedPassword = Instancio.create(HashedPassword::class.java)
                    val villageId = Instancio.create(VillageId::class.java)
                    val village = Instancio.of(Village::class.java)
                        .set(KSelect.field(Village::id), villageId)
                        .set(KSelect.field(Village::gameMasterUserId), gameMasterId)
                        .set(KSelect.field(Village::currentUserNumber), 2)
                        .create()
                    val villagePassword = "village-password"
                    val villageHashedPassword = Instancio.create(HashedPassword::class.java)

                    // and
                    every { VillageId.from(villageId.value.toString()) } returns villageId
                    every { villageRepository.selectVillageWithCurrentUsersById(villageId) } returns VillageCredentialWithUserCredentials(
                        village,
                        villageHashedPassword,
                        listOf(
                            UserCredential(gameMaster, gameMasterHashedPassword),
                            UserCredential(user1, user1HashedPassword),
                        ),
                    )
                    every { HashedPassword.doesMatch(villagePassword, villageHashedPassword) } returns true
                    every { UserId.from(user1Id.value.toString()) } returns user1Id
                    every { HashedPassword.doesMatch(user1Password, user1HashedPassword) } returns false

                    // when, then
                    val exception = shouldThrow<WerewolfException> {
                        target.invoke(
                            villageId.value.toString(),
                            villagePassword,
                            user1Id.value.toString(),
                            user1Password,
                        )
                    }
                    exception.message shouldBe "ユーザーパスワードが違います"

                    verify(exactly = 1) {
                        VillageId.from(villageId.value.toString())
                        villageRepository.selectVillageWithCurrentUsersById(villageId)
                        HashedPassword.doesMatch(villagePassword, villageHashedPassword)
                        UserId.from(user1Id.value.toString())
                        HashedPassword.doesMatch(user1Password, user1HashedPassword)
                    }
                }
            }
        }
    }
}

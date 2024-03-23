package com.example.backendkotlin

import com.example.backendkotlin.presentation.HelloController
import com.example.backendkotlin.presentation.response.HelloResponse
import com.example.backendkotlin.usecase.HelloUsecase
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.verify
import org.intellij.lang.annotations.Language
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@WebMvcTest(
    controllers = [HelloController::class],
)
class HelloControllerTest(
    private val mvc: MockMvc,
    @MockkBean private val helloUsecase: HelloUsecase,
) : DescribeSpec() {
    override fun extensions(): List<Extension> = listOf(SpringExtension)

    override suspend fun beforeTest(testCase: TestCase) {
        clearAllMocks()
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        confirmVerified(
            helloUsecase,
        )
    }

    init {
        describe("GET /hello") {
            it("200 OK") {
                // given:
                every { helloUsecase.getHelloResponse() } returns HelloResponse("Hello", null)

                // when:
                val req = MockMvcRequestBuilders
                    .get("/hello")
                    .contentType(MediaType.APPLICATION_JSON)
                val res = mvc.perform(req).andReturn().response

                // then:
                res.status shouldBe 200
                @Language("JSON")
                val expected = """
                    {
                      "value": "Hello",
                      "users": null
                    }
                """.trimIndent()
                res.contentAsString shouldEqualJson expected

                verify(exactly = 1) { helloUsecase.getHelloResponse() }
            }
        }
    }
}

package sh.alessandro.finances.api.calculator.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.calculator.domain.service.ClientService
import sh.alessandro.finances.api.calculator.domain.service.PlanService
import sh.alessandro.finances.api.calculator.dto.request.EntryDataDto
import sh.alessandro.finances.api.security.domain.models.User
import sh.alessandro.finances.builders.ClientBuilder
import sh.alessandro.finances.builders.PlanBuilder

@WebMvcTest(PlansController::class)
@AutoConfigureMockMvc(addFilters = false)
class PlansControllersTests {
    @MockkBean
    private lateinit var planService: PlanService

    @MockkBean
    private lateinit var clientService: ClientService

    @MockkBean
    private lateinit var authentication: Authentication

    @MockkBean
    private lateinit var securityContext: SecurityContext

    @Autowired
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        val client = ClientBuilder().build()

        every { authentication.principal } returns client.user
        every { securityContext.authentication } returns authentication
        every { clientService.findByUser(any(User::class)) } returns client

        every {
            planService.saveOne(
                any(EntryDataDto::class), any(Client::class)
            )
        } returns PlanBuilder(client = client).build()

        SecurityContextHolder.setContext(securityContext)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `createPlan should return CREATED (201) response with correct plan body`() {
        mockMvc.post("/api/v1/plans") {
            contentType = MediaType.APPLICATION_JSON
            header("Authorization", "Bearer token")
            content = """
                {
                    "title": "Car loan",
                    "loanAmount": 20000.0,
                    "downPaymentPercentage": 10.0,
                    "interestRate": 13.57,
                    "rateType": "EFFECTIVE",
                    "loanTerm": 24,
                    "currency": "PEN",
                    "portage": 5.0,
                    "loanDate": "2023-08-10",
                    "insurances": [
                        {
                            "type": "LIFE",
                            "percentage": 0.015
                        },
                        {
                            "type": "CAR",
                            "percentage": 0.1
                        }
                    ]
                }
            """
        }.andExpect {
            status {
                isCreated()
            }
        }

        verify { clientService.findByUser(any(User::class)) }
        verify {
            planService.saveOne(
                any(EntryDataDto::class), any(Client::class)
            )
        }
    }

    @Test
    fun `createPlan should return BAD REQUEST (400) response with correct plan body`() {
        mockMvc.post("/api/v1/plans") {
            contentType = MediaType.APPLICATION_JSON
            header("Authorization", "Bearer token")
            content = """
                {
                    "loanAmount": 20000.0,
                    "downPaymentPercentage": 10.0,
                }
            """
        }.andExpect {
            status { isBadRequest() }
        }
    }
}

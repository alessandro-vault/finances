package sh.alessandro.finances.api.calculator.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PlansControllersTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun createPlan() {
        mockMvc.post("/api/v1/plans") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "loanAmount": 20000.0,
                    "downPaymentPercentage": 10.0,
                    "interestRate": 13.57,
                    "loanTerm": 24,
                    "currency": "PEN",
                    "portage": 5.0,
                    "loanDate": "2023-08-10"
                }
            """
        }.andExpect {
            status { isCreated() }
        }
    }
}
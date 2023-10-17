package sh.alessandro.finances.api.calculator.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sh.alessandro.finances.api.calculator.domain.service.ClientService
import sh.alessandro.finances.api.calculator.dto.request.RegisterClientDto
import sh.alessandro.finances.api.calculator.dto.response.ClientRegistrationDto
import sh.alessandro.finances.api.security.dto.LoginDto

@RestController
@RequestMapping("api/v1/auth")
class ClientsController(
    private val clientService: ClientService
) {
    @PostMapping("clients/login")
    fun login(@RequestBody payload: LoginDto): ResponseEntity<Any> {
        return try {
            val client = clientService.login(payload.username, payload.password)
            ResponseEntity(client, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("clients/register")
    fun register(@RequestBody payload: RegisterClientDto): ResponseEntity<ClientRegistrationDto> {
        val client = clientService.register(payload)
        return ResponseEntity(ClientRegistrationDto(client), HttpStatus.CREATED)
    }
}
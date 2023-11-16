package sh.alessandro.finances.api.calculator.controller

import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import sh.alessandro.finances.api.calculator.domain.service.ClientService
import sh.alessandro.finances.api.calculator.dto.request.RegisterClientDto
import sh.alessandro.finances.api.calculator.dto.response.ClientRegistrationDto
import sh.alessandro.finances.api.calculator.dto.response.ShowClientDto
import sh.alessandro.finances.api.security.domain.models.User
import sh.alessandro.finances.api.security.dto.LoginDto

@RestController
@RequestMapping("api/v1/auth/clients")
class ClientsController(
    private val clientService: ClientService
) {
    @GetMapping("profile")
    fun profile(): ResponseEntity<Any> {
        println(SecurityContextHolder.getContext().authentication.principal as User)
        return try {
            val client =
                clientService.findByUser(SecurityContextHolder.getContext().authentication.principal as User)
                    ?: throw ChangeSetPersister.NotFoundException()

            ResponseEntity(ShowClientDto(client), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
    @PostMapping("login")
    fun login(@RequestBody payload: LoginDto): ResponseEntity<Any> {
        return try {
            val client = clientService.login(payload.username, payload.password)
            ResponseEntity(client, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("register")
    fun register(@RequestBody payload: RegisterClientDto): ResponseEntity<ClientRegistrationDto> {
        val client = clientService.register(payload)
        return ResponseEntity(ClientRegistrationDto(client), HttpStatus.CREATED)
    }
}
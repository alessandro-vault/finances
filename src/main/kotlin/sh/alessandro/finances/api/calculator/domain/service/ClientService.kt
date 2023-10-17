package sh.alessandro.finances.api.calculator.domain.service

import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.calculator.dto.request.RegisterClientDto
import sh.alessandro.finances.api.security.dto.LoginResponseDto

interface ClientService {
    fun findById(id: Long): Client?
    fun findByUsername(username: String): Client?
    fun findByToken(token: String): Client?
    fun login(username: String, password: String): LoginResponseDto
    fun register(client: RegisterClientDto): Client
}
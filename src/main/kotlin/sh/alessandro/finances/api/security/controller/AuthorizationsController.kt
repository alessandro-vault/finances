package sh.alessandro.finances.api.security.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sh.alessandro.finances.api.security.domain.enums.UserRole
import sh.alessandro.finances.api.security.domain.models.User
import sh.alessandro.finances.api.security.domain.service.HashService
import sh.alessandro.finances.api.security.domain.service.TokenService
import sh.alessandro.finances.api.security.domain.service.UserService
import sh.alessandro.finances.api.security.dto.ApiException
import sh.alessandro.finances.api.security.dto.LoginDto
import sh.alessandro.finances.api.security.dto.LoginResponseDto
import sh.alessandro.finances.api.security.dto.RegisterDto

@RestController
@RequestMapping("/api/v1/auth")

class AuthorizationsController(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userService.findByUserName(payload.username) ?: throw ApiException(400, "Login failed")

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ApiException(400, "Login failed")
        }

        return LoginResponseDto(
            token = tokenService.createToken(user)
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): ResponseEntity<Any> {
        if (userService.existsByUsername(payload.username)) {
            return ResponseEntity.badRequest().body(mapOf("error" to "Username already exists"))
        }

        val user = User(
            username = payload.username,
            password = hashService.hashBcrypt(payload.password),
            role = UserRole.ADMIN
        )

        val savedUser = userService.saveOne(user)

        return ResponseEntity.ok(
            LoginResponseDto(
                token = tokenService.createToken(savedUser)
            )
        )
    }
}
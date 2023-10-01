package sh.alessandro.finances.security.service

import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import sh.alessandro.finances.security.domain.User
import sh.alessandro.finances.security.domain.service.TokenService
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class TokenServiceImpl(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val userService: UserServiceImpl
) : TokenService{
    override fun createToken(user: User): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(user.username)
            .claim("userId", user.id)
            .build()
        return jwtEncoder.encode(
            JwtEncoderParameters.from(jwsHeader, claims)
        ).tokenValue
    }

    override fun parseToken(token: String): User? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as Long
            userService.findById(userId)
        } catch (e: Exception) {
            null
        }
    }
}
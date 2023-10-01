package sh.alessandro.finances.security.dto

data class LoginDto(
    val username: String,
    val password: String
)

data class RegisterDto(
    val username: String,
    val password: String
)

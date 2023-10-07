package sh.alessandro.finances.api.calculator.dto

data class NewClientDto(
    var firstName: String,
    var lastName: String,
    var username: String,
    var password: String,
)
package sh.alessandro.finances.api.calculator.domain.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import sh.alessandro.finances.api.security.domain.models.User

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @Column(name = "first_name")
    @NotNull
    var firstName : String,

    @Column(name = "last_name")
    @NotNull
    var lastName : String,

    @OneToOne()
    @JoinColumn(name = "user_id")
    var user : User? = null,

    @OneToMany(mappedBy = "client")
    var loans : List<Loan> = emptyList()
) {
    fun fullName() : String {
        return "$firstName $lastName"
    }
}
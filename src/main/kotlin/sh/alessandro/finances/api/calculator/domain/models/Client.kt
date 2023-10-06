package sh.alessandro.finances.api.calculator.domain.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import sh.alessandro.finances.api.security.domain.models.User

@Entity
@Table(name = "clients")
class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @Column(name = "first_name")
    @NotNull
    var firstName : String,

    @Column(name = "last_name")
    @NotNull
    var lastName : String,

    @OneToOne(mappedBy = "client")
    var user : User? = null,

    @OneToMany(mappedBy = "client")
    var loans : List<Loan> = emptyList()
)
package sh.alessandro.finances.calculator.domain.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import sh.alessandro.finances.security.domain.models.User

@Entity
@Table(name = "clients")
class Client(
    @Id
    var id : Long,

    @Column(name = "first_name")
    @NotNull
    var firstName : String,

    @Column(name = "last_name")
    @NotNull
    var lastName : String,

    @OneToOne(mappedBy = "client")
    var user : User,

    @OneToMany(mappedBy = "client")
    var loans : List<Loan> = emptyList()
)
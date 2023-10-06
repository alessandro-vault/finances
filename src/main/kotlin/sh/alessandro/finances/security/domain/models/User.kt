package sh.alessandro.finances.security.domain.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import sh.alessandro.finances.calculator.domain.models.Client
import java.util.*

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(unique = true, nullable = false)
    var username: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    var client: Client? = null
)
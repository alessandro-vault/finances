package sh.alessandro.finances.api.security.domain.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import sh.alessandro.finances.api.security.domain.enums.UserRole
import java.util.*

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(unique = true, nullable = false)
    var username: String = "",

    @Column(nullable = false)
    @JsonIgnore
    var password: String = "",

    @Column(nullable = false)
    var role: UserRole = UserRole.USER,

    @Column(name = "created_at")
    var createdAt: Date = Date(),
)
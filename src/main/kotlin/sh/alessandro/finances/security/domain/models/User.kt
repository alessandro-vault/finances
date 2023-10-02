package sh.alessandro.finances.security.domain.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", allocationSize = 1)
    var id: Long = 0,

    @Column(unique = true, nullable = false)
    var username: String = "",

    @Column(nullable = false)
    var password: String = "",
)
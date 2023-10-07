package sh.alessandro.finances.api.security.service

import org.springframework.stereotype.Service
import sh.alessandro.finances.api.security.domain.models.User
import sh.alessandro.finances.api.security.domain.persistence.UserRepository
import sh.alessandro.finances.api.security.domain.service.UserService
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService{
    override fun findById(id: UUID): User? {
        return userRepository.findById(id).orElse(null)
    }

    override fun findByUserName(username: String): User? {
        return userRepository.findByUsername(username)
    }

    override fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    override fun saveOne(user: User): User {
        return userRepository.save(user)
    }
}
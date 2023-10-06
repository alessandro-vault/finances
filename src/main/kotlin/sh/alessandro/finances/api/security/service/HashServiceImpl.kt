package sh.alessandro.finances.api.security.service

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import sh.alessandro.finances.api.security.domain.service.HashService

@Service
class HashServiceImpl : HashService {
    override fun checkBcrypt(password: String, hash: String): Boolean {
        return BCrypt.checkpw(password, hash)
    }

    override fun hashBcrypt(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt(10))
    }
}
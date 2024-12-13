package co.getaim.storage.persistence.mysql.persistence

import co.getaim.domain.blacklist.BlacklistDomain
import co.getaim.storage.persistence.mysql.entity.blacklist.BlacklistEntity
import org.springframework.stereotype.Service

@Service
class BlacklistPersistService(
    private val blacklistRepository: BlacklistRepository
) {
    fun save(invalidRefreshToken: String): BlacklistDomain {
        return blacklistRepository.save(BlacklistEntity(invalidRefreshToken)).toDomain()
    }

    fun existsByInvalidRefreshToken(token: String): Boolean {
        return blacklistRepository.existsByInvalidRefreshToken(token)
    }
}
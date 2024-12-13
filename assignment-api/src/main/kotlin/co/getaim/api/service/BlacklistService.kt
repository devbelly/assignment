package co.getaim.api.service

import co.getaim.storage.persistence.mysql.persistence.BlacklistPersistService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class BlacklistService(
    private val blacklistPersistService: BlacklistPersistService,
) {
    fun save(refreshToken: String) = blacklistPersistService.save(refreshToken)

    fun doesExist(refreshToken: String) = blacklistPersistService.existsByInvalidRefreshToken(refreshToken)
}
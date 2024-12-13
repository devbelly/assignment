package co.getaim.storage.persistence.mysql.persistence

import co.getaim.storage.persistence.mysql.entity.blacklist.BlacklistEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BlacklistRepository : JpaRepository<BlacklistEntity, Long> {
    fun existsByInvalidRefreshToken(token: String): Boolean
}
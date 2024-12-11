package co.getaim.storage.persistence.mysql.persistence

import co.getaim.storage.persistence.mysql.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun existsByInformationUserId(userId: String): Boolean
    fun findByInformationUserId(userId: String): UserEntity?
}
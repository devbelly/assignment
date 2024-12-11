package co.getaim.storage.persistence.mysql.persistence

import co.getaim.domain.user.UserDomain
import co.getaim.storage.persistence.mysql.entity.user.UserEntity
import org.springframework.stereotype.Service

@Service
class UserPersistService(
    private val userRepository: UserRepository
) {
    fun existsByUserId(userId: String): Boolean {
        return userRepository.existsByInformationUserId(userId)
    }

    fun findByUserId(userId: String): UserDomain? {
        return userRepository.findByInformationUserId(userId)?.toDomain()
    }

    fun save(userId: String, userName: String, password: String): UserDomain {
        return userRepository.save(UserEntity(userId, userName, password)).toDomain()
    }
}
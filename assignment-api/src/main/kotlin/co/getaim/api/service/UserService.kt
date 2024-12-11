package co.getaim.api.service

import co.getaim.api.dto.LoginUserCommand
import co.getaim.api.dto.LoginUserResponse
import co.getaim.api.dto.SignUpUserCommand
import co.getaim.api.dto.SignUpUserResponse
import co.getaim.common.utils.sha256Encrypt
import co.getaim.exception.user.UserException
import co.getaim.exception.user.UserExceptionType.*
import co.getaim.security.JwtTokenProvider
import co.getaim.storage.persistence.mysql.persistence.UserPersistService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class UserService(
    private val userPersistService: UserPersistService,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    fun existUser(userId: String): Boolean {
        return userPersistService.existsByUserId(userId)
    }

    fun signUp(request: SignUpUserCommand): SignUpUserResponse {
        if (userPersistService.existsByUserId(request.userId)) {
            throw UserException(ALREADY_EXIST_USER)
        }

        val user = userPersistService.save(request.userId, request.username, request.password)

        return SignUpUserResponse(user)
    }

    fun login(request: LoginUserCommand): LoginUserResponse {
        val user = userPersistService.findByUserId(request.userId)
            ?: throw UserException(NOT_FOUND_USER)

        if (!user.authenticate(sha256Encrypt(request.password))) {
            throw UserException(INVALID_PASSWORD_ACCESS_DENIED)
        }

        return LoginUserResponse(
            jwtTokenProvider.createAccessToken(user.userId),
            jwtTokenProvider.createRefreshToken(user.userId)
        )
    }
}
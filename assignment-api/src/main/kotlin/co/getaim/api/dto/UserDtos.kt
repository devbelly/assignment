package co.getaim.api.dto

import co.getaim.domain.user.UserDomain

data class SignUpUserCommand(
    val userId: String,
    val username: String,
    val password: String,
)

data class LoginUserCommand(
    val userId: String,
    val password: String,
)

data class SignUpUserResponse(
    val userId: String,
    val username: String,
) {
    constructor(user: UserDomain) : this(user.userId, user.username)
}

data class LoginUserResponse(
    val accessToken: String,
    val refreshToken: String,
)

data class LogoutUserCommand(
    val refreshToken: String,
)
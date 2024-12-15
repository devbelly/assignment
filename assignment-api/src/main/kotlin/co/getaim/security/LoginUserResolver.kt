package co.getaim.security

import co.getaim.api.service.BlacklistService
import co.getaim.domain.user.UserDomain
import co.getaim.exception.blacklist.BlacklistException
import co.getaim.exception.blacklist.BlacklistExceptionType.ALREADY_LOGOUT
import co.getaim.exception.token.TokenException
import co.getaim.exception.token.TokenExceptionType.*
import co.getaim.exception.user.UserException
import co.getaim.exception.user.UserExceptionType
import co.getaim.exception.user.UserExceptionType.FORBIDDEN_USER
import co.getaim.storage.persistence.mysql.persistence.UserPersistService
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

private const val BEARER = "Bearer"

@Component
class LoginUserResolver(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userPersistService: UserPersistService,
    private val blacklistService: BlacklistService,
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(LoginUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): UserDomain? {
        val token = extractBearerToken(webRequest)
        jwtTokenProvider.validateToken(token)

        if (blacklistService.doesExist(token)) {
            throw BlacklistException(ALREADY_LOGOUT)
        }

        val userId = jwtTokenProvider.getSubject(token)
        val user = userPersistService.findByUserId(userId)

//        validateIfAdministrator(parameter, user)
        return user
    }

    private fun extractBearerToken(request: NativeWebRequest): String {
        val authorization =
            request.getHeader(HttpHeaders.AUTHORIZATION) ?: throw TokenException(NOT_FOUND_TOKEN)
        val (tokenType, token) = splitToTokenFormat(authorization)
        if (tokenType != BEARER) {
            throw TokenException(WRONG_TOKEN_TYPE)
        }
        return token
    }

    private fun splitToTokenFormat(authorization: String): Pair<String, String> {
        return try {
            val tokenFormat = authorization.split(" ")
            tokenFormat[0] to tokenFormat[1]
        } catch (e: IndexOutOfBoundsException) {
            throw TokenException(UNSUPPORTED_TOKEN)
        }
    }

    private fun validateIfAdministrator(parameter: MethodParameter, user: UserDomain?) {
        val annotation = parameter.getParameterAnnotation(LoginUser::class.java)
        if (annotation?.administrator == true && !user!!.isAdmin) {

            throw UserException(FORBIDDEN_USER)
        }
    }
}

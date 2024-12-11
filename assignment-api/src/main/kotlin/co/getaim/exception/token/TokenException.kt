package co.getaim.exception.token

import co.getaim.exception.BaseException

class TokenException(
    private val exceptionType: TokenExceptionType,
) : BaseException() {
    override fun exceptionType(): TokenExceptionType {
        return exceptionType
    }
}

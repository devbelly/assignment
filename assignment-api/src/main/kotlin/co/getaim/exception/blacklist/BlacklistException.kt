package co.getaim.exception.blacklist

import co.getaim.exception.BaseException

class BlacklistException(
    private val exceptionType: BlacklistExceptionType,
) : BaseException() {
    override fun exceptionType(): BlacklistExceptionType {
        return exceptionType
    }
}
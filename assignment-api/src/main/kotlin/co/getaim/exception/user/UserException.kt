package co.getaim.exception.user

import co.getaim.exception.BaseException

class UserException(
    private val exceptionType: UserExceptionType,
) : BaseException() {
    override fun exceptionType(): UserExceptionType {
        return exceptionType
    }
}

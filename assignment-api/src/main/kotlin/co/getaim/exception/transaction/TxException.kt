package co.getaim.exception.transaction

import co.getaim.exception.BaseException
import co.getaim.exception.blacklist.BlacklistExceptionType

class TxException(
    private val exceptionType: TxExceptionType,
) : BaseException() {
    override fun exceptionType(): TxExceptionType {
        return exceptionType
    }
}
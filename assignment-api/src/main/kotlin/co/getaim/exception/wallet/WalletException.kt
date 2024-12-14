package co.getaim.exception.wallet

import co.getaim.exception.BaseException

class WalletException(
    private val exceptionType: WalletExceptionType,
) : BaseException() {
    override fun exceptionType(): WalletExceptionType {
        return exceptionType
    }
}
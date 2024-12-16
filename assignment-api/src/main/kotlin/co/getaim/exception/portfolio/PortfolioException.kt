package co.getaim.exception.portfolio

import co.getaim.exception.BaseException

class PortfolioException(
    private val exceptionType: PortfolioExceptionType,
) : BaseException() {
    override fun exceptionType(): PortfolioExceptionType {
        return exceptionType
    }
}

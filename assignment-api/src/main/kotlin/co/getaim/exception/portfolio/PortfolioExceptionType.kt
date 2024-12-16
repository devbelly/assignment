package co.getaim.exception.portfolio

import co.getaim.exception.BaseExceptionType
import org.springframework.http.HttpStatus

enum class PortfolioExceptionType(
    private val httpStatus: HttpStatus,
    private val code: String,
    private val errorMessage: String,
) : BaseExceptionType {

    MONEY_EXCEED(HttpStatus.BAD_REQUEST, "P01", "가용 가능한 돈의 범위를 초과했습니다.");

    override fun httpStatus(): HttpStatus {
        return httpStatus
    }

    override fun code(): String {
        return code
    }

    override fun errorMessage(): String {
        return errorMessage
    }
}
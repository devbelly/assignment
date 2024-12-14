package co.getaim.exception.transaction

import co.getaim.exception.BaseExceptionType
import org.springframework.http.HttpStatus

enum class TxExceptionType(
    private val httpStatus: HttpStatus,
    private val code: String,
    private val errorMessage: String,
) : BaseExceptionType {

    TRANSACTION_NOT_FOUNT(HttpStatus.NOT_FOUND, "TX01", "트랜잭션을 찾을 수 없습니다."),
    ;

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

package co.getaim.exception.wallet

import co.getaim.exception.BaseExceptionType
import org.springframework.http.HttpStatus

enum class WalletExceptionType(
    private val httpStatus: HttpStatus,
    private val code: String,
    private val errorMessage: String,
) : BaseExceptionType {

    NOT_FOUND_WALLET(HttpStatus.NOT_FOUND, "C40", "지갑이 존재하지 않습니다."),
    NOT_ENOUGH_MONEY(HttpStatus.BAD_REQUEST, "C41", "잔액이 부족합니다."),
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
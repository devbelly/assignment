package co.getaim.exception.user

import co.getaim.exception.BaseExceptionType
import org.springframework.http.HttpStatus

enum class UserExceptionType(
    private val httpStatus: HttpStatus,
    private val code: String,
    private val errorMessage: String,
) : BaseExceptionType {

    INVALID_PASSWORD_ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "C01", "올바르지 않은 비밀번호 입니다."),
    INVALID_USER_INFORMATION(HttpStatus.UNAUTHORIZED, "C02", "사용자 정보가 일치하지 않습니다."),

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "C40", "존재하지 않는 사용자입니다."),
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "C41", "이미 존재하는 사용자입니다."),
    FORBIDDEN_USER(HttpStatus.FORBIDDEN, "C42", "사용자 권한이 없습니다."),
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
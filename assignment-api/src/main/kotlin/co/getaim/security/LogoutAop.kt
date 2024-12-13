package co.getaim.security

import co.getaim.api.dto.LoginUserResponse
import co.getaim.api.dto.LogoutUserResponse
import co.getaim.common.utils.logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Aspect
@Component
class LogoutAop {

    private val log = logger()

    @Pointcut("execution(* co.getaim.api.controller.UserController.logout(..))")
    fun logoutPointcut() {

    }

    @Around("logoutPointcut()")
    fun logLogout(joinPoint: ProceedingJoinPoint): Any {
        val result = joinPoint.proceed()
        val userId = ((result as? ResponseEntity<*>)?.body as? LogoutUserResponse)?.userId

        log.info("사용자가 로그아웃 했습니다. : $userId")

        return result
    }
}
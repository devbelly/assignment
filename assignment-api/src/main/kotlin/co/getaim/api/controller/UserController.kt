package co.getaim.api.controller

import co.getaim.api.dto.LoginUserCommand
import co.getaim.api.dto.LoginUserResponse
import co.getaim.api.dto.SignUpUserCommand
import co.getaim.api.dto.SignUpUserResponse
import co.getaim.api.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun signUp(@RequestBody request: SignUpUserCommand): ResponseEntity<SignUpUserResponse> {
        val res = userService.signUp(request)
        return ResponseEntity.ok(res)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginUserCommand): ResponseEntity<LoginUserResponse> {
        val res = userService.login(request)
        return ResponseEntity.ok(res)
    }
}
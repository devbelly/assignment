package co.getaim.api.controller

import co.getaim.api.dto.CreateRequestCommand
import co.getaim.api.service.RequestService
import co.getaim.domain.user.UserDomain
import co.getaim.security.LoginUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/request")
@RestController
class RequestController(
    private val requestService: RequestService
) {
     @PostMapping
     fun advice(@LoginUser user: UserDomain, @RequestBody request: CreateRequestCommand): ResponseEntity<Unit> {
         requestService.create(user.id, request)
         return ResponseEntity.noContent().build()
     }
}
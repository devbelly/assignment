package co.getaim.api.controller

import co.getaim.api.dto.CreatePortfolioCommand
import co.getaim.api.service.PortfolioService
import co.getaim.domain.user.UserDomain
import co.getaim.security.LoginUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/portfolio")
@RestController
class PortfolioController(
    private val portfolioService: PortfolioService
) {
    @PostMapping
    fun create(@LoginUser user: UserDomain, @RequestBody req: CreatePortfolioCommand): ResponseEntity<Unit> {
        portfolioService.save(user.id, req)
        return ResponseEntity.noContent().build()
    }
}
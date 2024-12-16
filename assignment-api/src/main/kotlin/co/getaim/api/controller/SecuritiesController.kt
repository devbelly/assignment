package co.getaim.api.controller

import co.getaim.api.dto.CreateSecuritiesCommand
import co.getaim.api.dto.UpdateSecuritiesCommand
import co.getaim.api.service.SecuritiesService
import co.getaim.domain.user.UserDomain
import co.getaim.security.LoginUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/securities")
@RestController
class SecuritiesController(
    private val securitiesService: SecuritiesService
) {
    @PostMapping
    fun create(
        @LoginUser(administrator = true) user: UserDomain,
        @RequestBody request: CreateSecuritiesCommand
    ): ResponseEntity<Unit> {
        securitiesService.save(request)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{securitiesId}")
    fun update(
        @LoginUser(administrator = true) user: UserDomain,
        @PathVariable securitiesId: Long,
        @RequestBody request: UpdateSecuritiesCommand
    ): ResponseEntity<Unit> {
        securitiesService.update(securitiesId, request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{securitiesId}")
    fun delete(
        @LoginUser(administrator = true) user: UserDomain,
        @PathVariable securitiesId: Long
    ): ResponseEntity<Unit> {
        securitiesService.delete(securitiesId)
        return ResponseEntity.noContent().build()
    }
}

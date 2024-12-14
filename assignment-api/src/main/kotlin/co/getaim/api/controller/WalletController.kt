package co.getaim.api.controller

import co.getaim.api.dto.*
import co.getaim.api.service.WalletService
import co.getaim.domain.user.UserDomain
import co.getaim.security.LoginUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/wallet")
@RestController
class WalletController(
    private val walletService: WalletService
) {
    @PostMapping
    fun createWallet(@LoginUser user: UserDomain): ResponseEntity<Unit> {
        walletService.createWallet(user.id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/deposit")
    fun increaseMoney(@LoginUser user: UserDomain, @RequestBody request: DepositWalletCommand): ResponseEntity<DepositWalletResponse> {
        val res = walletService.increaseMoney(user.id, request)
        return ResponseEntity.ok(res)
    }

    @PutMapping("/withdrawal")
    fun decreaseMoney(@LoginUser user: UserDomain, @RequestBody request: WithdrawalWalletCommand): ResponseEntity<WithdrawalWalletResponse> {
        val res = walletService.decreaseMoney(user.id, request)
        return ResponseEntity.ok(res)
    }
}
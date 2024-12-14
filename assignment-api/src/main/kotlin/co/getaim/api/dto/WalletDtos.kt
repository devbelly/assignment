package co.getaim.api.dto

import java.math.BigDecimal

data class DepositWalletCommand(
    val amount: BigDecimal,
)

data class WithdrawalWalletCommand(
    val amount: BigDecimal,
)

data class DepositWalletResponse(
    val userId : Long,
    val walletId: Long,
    val money: BigDecimal,
)

data class WithdrawalWalletResponse(
    val userId : Long,
    val walletId: Long,
    val money: BigDecimal,
)

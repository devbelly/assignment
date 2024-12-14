package co.getaim.api.dto.event

import java.math.BigDecimal

data class WithdrawalCreatedEvent(
    val userId: Long,
    val amount: BigDecimal
)
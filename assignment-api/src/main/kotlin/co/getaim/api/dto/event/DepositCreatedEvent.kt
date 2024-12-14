package co.getaim.api.dto.event

import java.math.BigDecimal

class DepositCreatedEvent(
    val userId: Long,
    val amount: BigDecimal
)
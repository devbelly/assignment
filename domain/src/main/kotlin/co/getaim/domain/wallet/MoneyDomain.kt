package co.getaim.domain.wallet

import java.math.BigDecimal

data class MoneyDomain(
    val price: BigDecimal,
    val currency: String
)
package co.getaim.domain.securities

import java.math.BigDecimal

data class SecuritiesDomain(
    val id: Long,
    val securityName: String,
    val securityCode: String,
    val price: BigDecimal
)

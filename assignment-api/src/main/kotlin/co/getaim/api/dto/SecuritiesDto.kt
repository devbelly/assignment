package co.getaim.api.dto

import java.math.BigDecimal

data class CreateSecuritiesCommand(
    val securityName: String,
    val securityCode: String,
    val price: BigDecimal
)

data class UpdateSecuritiesCommand(
    val securityName: String,
    val securityCode: String,
    val price: BigDecimal
)
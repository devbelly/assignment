package co.getaim.api.dto

import co.getaim.domain.request.RiskType

data class CreateRequestCommand(
    val type: RiskType,
)
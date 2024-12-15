package co.getaim.domain.request

class RequestDomain(
    val id: Long,
    val userId: Long,
    val status: RequestStatus,
    val riskType: RiskType
)
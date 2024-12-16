package co.getaim.domain.request

import java.math.BigDecimal

class RequestDomain(
    val id: Long,
    val userId: Long,
    val status: RequestStatus,
    val riskType: RiskType
){
    fun maximumMoney(amount: BigDecimal): BigDecimal {
        return amount.multiply(BigDecimal(riskType.value))
    }
}
package co.getaim.domain.investment

data class InvestmentDomain(
    val portfolioId: Long,
    val securitiesId: Long,
    val quantity: Long,
    val id: Long = 0L
)

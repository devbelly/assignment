package co.getaim.api.dto

data class CreatePortfolioCommand(
    val requestId: Long,
    val securitiesList: List<BuySecuritiesCommand>
)


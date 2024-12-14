package co.getaim.domain.wallet


data class WalletDomain(
    val id: Long,
    val version: Long,
    val userId: Long,
    val money: MoneyDomain
)

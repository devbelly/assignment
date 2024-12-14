package co.getaim.domain.transaction

import co.getaim.domain.wallet.MoneyDomain
import java.time.LocalDateTime

data class TransactionDomain(
    val id: Long,
    val userId: Long,
    val type: TransactionType,
    val amount: MoneyDomain,
    val createdDateTime: LocalDateTime,
    val status: TransactionStatus,
    val description: String
){
    fun success() {
        val status = TransactionStatus.COMPLETED
    }
}

package co.getaim.storage.persistence.mysql.entity.transaction

import co.getaim.domain.transaction.TransactionDomain
import co.getaim.domain.transaction.TransactionStatus
import co.getaim.domain.transaction.TransactionType
import co.getaim.storage.persistence.mysql.entity.BaseEntity
import co.getaim.storage.persistence.mysql.entity.wallet.Money
import jakarta.persistence.*
import jakarta.persistence.EnumType.*
import java.time.LocalDateTime

@Entity
@Table(name = "transaction")
class TransactionEntity(
    @Column(nullable = false)
    val userId: Long,

    @Enumerated(STRING)
    @Column(nullable = false)
    val type: TransactionType,

    @Column(nullable = false)
    val amount: Money,

    @Column(nullable = false)
    val createdDateTime: LocalDateTime = LocalDateTime.now(),

    @Enumerated(STRING)
    @Column(nullable = false)
    val status: TransactionStatus = TransactionStatus.PROCESSING,

    @Column(nullable = false)
    val description: String = "",

    id: Long = 0L
): BaseEntity(id){

    fun toDomain(): TransactionDomain =
        TransactionDomain(
            id = id,
            userId = userId,
            type = type,
            amount = amount.toDomain(),
            createdDateTime = createdDateTime,
            status = status,
            description = description
        )
}


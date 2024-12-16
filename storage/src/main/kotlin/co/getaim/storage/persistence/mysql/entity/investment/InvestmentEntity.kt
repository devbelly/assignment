package co.getaim.storage.persistence.mysql.entity.investment

import co.getaim.domain.investment.InvestmentDomain
import co.getaim.storage.persistence.mysql.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "investment")
class InvestmentEntity(
    @Column(nullable = false)
    val portfolioId: Long,

    @Column(nullable = false)
    val securitiesId: Long,

    @Column(nullable = false)
    val quantity: Long,

    id: Long = 0L
) : BaseEntity(id){
    fun toDomain() = InvestmentDomain(
        portfolioId = portfolioId,
        securitiesId = securitiesId,
        quantity = quantity,
        id = id
    )
}

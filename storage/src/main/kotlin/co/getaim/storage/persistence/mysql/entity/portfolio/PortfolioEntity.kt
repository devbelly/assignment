package co.getaim.storage.persistence.mysql.entity.portfolio

import co.getaim.domain.portfolio.PortfolioDomain
import co.getaim.storage.persistence.mysql.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class PortfolioEntity(
    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val requestId: Long,

    id: Long = 0L
): BaseEntity(id) {
    fun toDomain() = PortfolioDomain(
        userId = userId,
        requestId = requestId,
        id = id
    )
}

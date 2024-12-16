package co.getaim.storage.persistence.mysql.persistence

import co.getaim.domain.portfolio.PortfolioDomain
import co.getaim.storage.persistence.mysql.entity.portfolio.PortfolioEntity
import org.springframework.stereotype.Service

@Service
class PortfolioPersistService (
    private val portfolioRepository: PortfolioRepository
){
    fun save(userId: Long, requestId: Long) : PortfolioDomain {
        return portfolioRepository.save(PortfolioEntity(userId = userId, requestId = requestId)).toDomain()
    }
}

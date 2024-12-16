package co.getaim.storage.persistence.mysql.persistence

import co.getaim.domain.investment.InvestmentDomain
import co.getaim.storage.persistence.mysql.entity.investment.InvestmentEntity
import org.springframework.stereotype.Service

@Service
class InvestmentPersistService(
    private val investmentRepository: InvestmentRepository
) {
    fun save(portfolioId: Long, securitiesId: Long, quantity: Long) : InvestmentDomain {
        return investmentRepository.save(InvestmentEntity(portfolioId = portfolioId, securitiesId = securitiesId, quantity = quantity)).toDomain()
    }
}
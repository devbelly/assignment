package co.getaim.api.service

import co.getaim.domain.investment.InvestmentDomain
import co.getaim.storage.persistence.mysql.persistence.InvestmentPersistService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InvestmentService(
    private val investmentPersistService: InvestmentPersistService
) {
    fun save(portfolioId: Long, securitiesId: Long, amount: Long) : InvestmentDomain {
        return investmentPersistService.save(portfolioId, securitiesId, amount)
    }
}

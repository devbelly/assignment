package co.getaim.storage.persistence.mysql.persistence

import co.getaim.domain.securities.SecuritiesDomain
import co.getaim.storage.persistence.mysql.entity.securities.SecuritiesEntity
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class SecuritiesPersistService(
    private val repository: SecuritiesRepository
) {
    fun save(securityName: String, securityCode: String, price: BigDecimal): SecuritiesDomain {
        return repository.save(
            SecuritiesEntity(
                securityName = securityName,
                securityCode = securityCode,
                price = price
            )
        ).toDomain()
    }

    fun update(
        securitiesId: Long,
        securityName: String,
        securityCode: String,
        price: BigDecimal
    ): SecuritiesDomain {
        return repository.update(securitiesId, securityName, securityCode, price)!!.toDomain()
    }

    fun deleteById(securitiesId: Long) {
        repository.deleteById(securitiesId)
    }
}
package co.getaim.api.service

import co.getaim.api.dto.CreateSecuritiesCommand
import co.getaim.api.dto.UpdateSecuritiesCommand
import co.getaim.storage.persistence.mysql.persistence.SecuritiesPersistService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SecuritiesService(
    private val securitiesPersistService: SecuritiesPersistService
) {
    fun save(request: CreateSecuritiesCommand): Unit {
        securitiesPersistService.save(request.securityName, request.securityCode, request.price)
    }

    fun update(securitiesId: Long, request: UpdateSecuritiesCommand): Unit {
        securitiesPersistService.update(
            securitiesId,
            request.securityName,
            request.securityCode,
            request.price
        )
    }

    fun delete(securitiesId: Long): Unit {
        securitiesPersistService.deleteById(securitiesId)
    }
}
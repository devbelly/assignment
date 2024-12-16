package co.getaim.storage.persistence.mysql.persistence

import co.getaim.domain.request.RequestDomain
import co.getaim.domain.request.RequestStatus
import co.getaim.domain.request.RiskType
import co.getaim.storage.persistence.mysql.entity.request.RequestEntity
import org.springframework.stereotype.Service

@Service
class RequestPersistService(
    private val requestRepository: RequestRepository
) {
    fun save(userId: Long, type: RiskType): RequestDomain {
        return requestRepository.save(RequestEntity(riskType = type, userId = userId)).toDomain()
    }

    fun findById(requestId: Long): RequestDomain {
        return requestRepository.findById(requestId).orElseThrow().toDomain()
    }

    fun update(requestId: Long, type: RiskType, status: RequestStatus) : RequestDomain {
        return requestRepository.update(requestId, type, status)!!.toDomain()
    }


}
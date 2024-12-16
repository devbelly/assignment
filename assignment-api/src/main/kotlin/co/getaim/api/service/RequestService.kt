package co.getaim.api.service

import co.getaim.api.dto.CreateRequestCommand
import co.getaim.domain.request.RequestDomain
import co.getaim.domain.request.RequestStatus
import co.getaim.domain.request.RiskType
import co.getaim.storage.persistence.mysql.persistence.RequestPersistService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RequestService(
    private val requestPersistService: RequestPersistService
) {
    fun find(requestId: Long) : RequestDomain {
        return requestPersistService.findById(requestId)
    }

    fun create(userId: Long, request: CreateRequestCommand) {
        requestPersistService.save(userId, request.type)
    }

    fun update(requestId: Long, type: RiskType, status: RequestStatus) {
        requestPersistService.update(requestId, type, status)
    }
}
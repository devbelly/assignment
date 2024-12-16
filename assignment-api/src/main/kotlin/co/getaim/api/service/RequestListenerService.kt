package co.getaim.api.service

import co.getaim.api.dto.event.RequestFailedEvent
import co.getaim.api.dto.event.RequestSucceededEvent
import co.getaim.domain.request.RequestStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRES_NEW
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

@Service
@Transactional
class RequestListenerService(
    private val requestService: RequestService
) {
    @Transactional(propagation = REQUIRES_NEW)
    @TransactionalEventListener
    fun requestSuccess(event: RequestSucceededEvent) {
        val request = requestService.find(event.requestId)

        requestService.update(request.id, request.riskType, RequestStatus.COMPLETED)
    }

    @Transactional(propagation = REQUIRES_NEW)
    @TransactionalEventListener
    fun requestFail(event: RequestFailedEvent) {
        val request = requestService.find(event.requestId)

        requestService.update(request.id, request.riskType, RequestStatus.REJECTED)
    }
}

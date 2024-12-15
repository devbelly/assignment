package co.getaim.api.service

import co.getaim.api.dto.CreateRequestCommand
import co.getaim.storage.persistence.mysql.persistence.RequestPersistService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RequestService(
    private val requestPersistService: RequestPersistService
) {
    fun create(userId: Long, request: CreateRequestCommand) {
        requestPersistService.save(userId, request.type)
    }
}
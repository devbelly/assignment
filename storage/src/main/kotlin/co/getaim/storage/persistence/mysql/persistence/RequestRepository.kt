package co.getaim.storage.persistence.mysql.persistence

import co.getaim.storage.persistence.mysql.entity.request.RequestEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RequestRepository : JpaRepository<RequestEntity, Long>, RequestRepositorySupport {
}
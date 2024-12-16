package co.getaim.storage.persistence.mysql.entity.request

import co.getaim.domain.request.RequestDomain
import co.getaim.domain.request.RequestStatus
import co.getaim.domain.request.RiskType
import co.getaim.storage.persistence.mysql.entity.BaseEntity
import jakarta.persistence.*
import jakarta.persistence.EnumType.*

@Entity
@Table(name = "request")
class RequestEntity(
    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    @Enumerated(STRING)
    val riskType: RiskType,

    @Column(nullable = false)
    @Enumerated(STRING)
    val status: RequestStatus = RequestStatus.REGISTERED,

    id: Long = 0L,
): BaseEntity(id){
    fun toDomain() = RequestDomain(
        id = id,
        userId = userId,
        riskType = riskType,
        status = status
    )
}

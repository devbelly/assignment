package co.getaim.storage.persistence.mysql.entity.request

import co.getaim.domain.request.RequestDomain
import co.getaim.domain.request.RequestStatus
import co.getaim.domain.request.RiskType
import co.getaim.storage.persistence.mysql.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.EnumType.*
import jakarta.persistence.Enumerated

@Entity
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

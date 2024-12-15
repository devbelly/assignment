package co.getaim.storage.persistence.mysql.entity.securities

import co.getaim.domain.securities.SecuritiesDomain
import co.getaim.storage.persistence.mysql.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "securities")
class SecuritiesEntity(
    @Column(nullable = false)
    val securityName: String,

    @Column(nullable = false)
    val securityCode: String,

    @Column(nullable = false)
    val price: BigDecimal,

    id: Long = 0L
) : BaseEntity(id) {

    fun toDomain() = SecuritiesDomain(
        id = id,
        securityName = securityName,
        securityCode = securityCode,
        price = price
    )
}

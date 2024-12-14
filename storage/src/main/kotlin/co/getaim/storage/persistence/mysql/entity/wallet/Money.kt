package co.getaim.storage.persistence.mysql.entity.wallet

import co.getaim.domain.wallet.MoneyDomain
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
class Money(
    @Column(nullable = false)
    var price: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var currency: String = "KRW"
) {

    fun toDomain(): MoneyDomain =
        MoneyDomain(
            price = price,
            currency = currency
        )
}
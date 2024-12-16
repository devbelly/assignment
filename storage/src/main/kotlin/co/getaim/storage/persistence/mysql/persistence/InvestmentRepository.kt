package co.getaim.storage.persistence.mysql.persistence

import co.getaim.storage.persistence.mysql.entity.investment.InvestmentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface InvestmentRepository : JpaRepository<InvestmentEntity, Long> {
}
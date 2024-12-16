package co.getaim.storage.persistence.mysql.persistence

import co.getaim.storage.persistence.mysql.entity.portfolio.PortfolioEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PortfolioRepository : JpaRepository<PortfolioEntity, Long> {

}
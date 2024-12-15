package co.getaim.storage.persistence.mysql.persistence

import co.getaim.storage.persistence.mysql.entity.securities.SecuritiesEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SecuritiesRepository : JpaRepository<SecuritiesEntity, Long>, SecuritiesRepositorySupport {
}
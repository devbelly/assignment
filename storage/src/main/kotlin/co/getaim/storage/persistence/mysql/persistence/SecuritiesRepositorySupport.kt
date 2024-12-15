package co.getaim.storage.persistence.mysql.persistence

import co.getaim.storage.persistence.mysql.entity.securities.SecuritiesEntity
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.support.spring.data.jpa.extension.createQuery
import jakarta.persistence.EntityManager
import java.math.BigDecimal

interface SecuritiesRepositorySupport {
    fun update(
        securitiesId: Long,
        securityName: String,
        securityCode: String,
        price: BigDecimal
    ): SecuritiesEntity?
}

class SecuritiesRepositoryImpl(
    private val em: EntityManager,
    private val context: JpqlRenderContext
) : SecuritiesRepositorySupport {
    override fun update(
        securitiesId: Long,
        securityName: String,
        securityCode: String,
        price: BigDecimal
    ): SecuritiesEntity? {
        val query = jpql {
            update(entity(SecuritiesEntity::class))
                .set(path(SecuritiesEntity::securityName), securityName)
                .set(path(SecuritiesEntity::securityCode), securityCode)
                .set(path(SecuritiesEntity::price), price)
                .where(path(SecuritiesEntity::id).eq(securitiesId))
        }

        return em.createQuery(query, context).executeUpdate().takeIf { it == 1 }?.let {
            val securities = em.find(SecuritiesEntity::class.java, securitiesId)
            em.refresh(securities)
            return securities
        }
    }
}
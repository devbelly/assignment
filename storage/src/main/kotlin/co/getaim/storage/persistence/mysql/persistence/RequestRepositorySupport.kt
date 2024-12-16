package co.getaim.storage.persistence.mysql.persistence

import co.getaim.domain.request.RequestStatus
import co.getaim.domain.request.RiskType
import co.getaim.storage.persistence.mysql.entity.request.RequestEntity
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.support.spring.data.jpa.extension.createQuery
import jakarta.persistence.EntityManager

interface RequestRepositorySupport {
    fun update(requestId: Long, type: RiskType, status: RequestStatus): RequestEntity?
}

class RequestRepositoryImpl(
    private val em: EntityManager,
    private val context: JpqlRenderContext
) : RequestRepositorySupport {
    override fun update(requestId: Long, type: RiskType, status: RequestStatus): RequestEntity? {
        val jpql = jpql {
            update(entity(RequestEntity::class))
                .set(path(RequestEntity::riskType), type)
                .set(path(RequestEntity::status), status)
                .where(path(RequestEntity::id).eq(requestId))
        }
        return em.createQuery(jpql, context).executeUpdate().takeIf { it == 1 }?.let {
            val request = em.find(RequestEntity::class.java, requestId)
            em.refresh(request)
            return request
        }
    }
}
package co.getaim.storage.persistence.mysql.persistence

import co.getaim.domain.transaction.TransactionStatus
import co.getaim.storage.persistence.mysql.entity.transaction.TransactionEntity
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.support.spring.data.jpa.extension.createQuery
import jakarta.persistence.EntityManager

interface TransactionEntityRepositorySupport {
    fun updateStatus(id: Long, status: TransactionStatus): Int
}

class TransactionRepositoryImpl(
    private val em: EntityManager,
    private val context: JpqlRenderContext
) : TransactionEntityRepositorySupport {
    override fun updateStatus(id: Long, status: TransactionStatus): Int {
        val query = jpql {
            update(entity(TransactionEntity::class))
                .set(path(TransactionEntity::status), status)
                .where(path(TransactionEntity::id).eq(id))
        }

        return em.createQuery(query, context).executeUpdate()
    }
}
package co.getaim.storage.persistence.mysql.persistence

import co.getaim.storage.persistence.mysql.entity.wallet.Money
import co.getaim.storage.persistence.mysql.entity.wallet.WalletEntity
import co.getaim.storage.spec.WalletUpdateSpec
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.support.spring.data.jpa.extension.createQuery
import jakarta.persistence.EntityManager

interface WalletEntityRepositorySupport {
    fun increaseMoney(spec: WalletUpdateSpec): WalletEntity?
    fun decreaseMoney(spec: WalletUpdateSpec): WalletEntity?
}

class WalletRepositoryImpl(
    private val em: EntityManager,
    private val context: JpqlRenderContext
) : WalletEntityRepositorySupport {
    override fun increaseMoney(spec: WalletUpdateSpec): WalletEntity? {
        val query = jpql {
            update(entity(WalletEntity::class))
                .set(path(WalletEntity::version), spec.version + 1)
                .set(path(WalletEntity::money)(Money::price), spec.remainingMoney + spec.amount)
                .whereAnd(
                    path(WalletEntity::id).eq(spec.walletId),
                    path(WalletEntity::version).eq(spec.version)
                )
        }
        return em.createQuery(query, context).executeUpdate().takeIf { it == 1 }?.let {
            val wallet = em.find(WalletEntity::class.java, spec.walletId)
            em.refresh(wallet)
            return wallet
        }
    }

    override fun decreaseMoney(spec: WalletUpdateSpec): WalletEntity? {
        val query = jpql {
            update(entity(WalletEntity::class))
                .set(path(WalletEntity::version), spec.version + 1)
                .set(path(WalletEntity::money)(Money::price), spec.remainingMoney - spec.amount)
                .whereAnd(
                    path(WalletEntity::id).eq(spec.walletId),
                    path(WalletEntity::version).eq(spec.version)
                )
        }
        return em.createQuery(query, context).executeUpdate().takeIf { it == 1 }?.let {
            val wallet = em.find(WalletEntity::class.java, spec.walletId)
            em.refresh(wallet)
            return wallet
        }
    }
}
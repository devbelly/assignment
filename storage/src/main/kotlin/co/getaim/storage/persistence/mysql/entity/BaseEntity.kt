package co.getaim.storage.persistence.mysql.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.*

@MappedSuperclass
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) {
    override fun equals(other: Any?): Boolean =
        Objects.equals(id, (other as? BaseEntity)?.id)

    override fun hashCode(): Int =
        Objects.hashCode(id)
}
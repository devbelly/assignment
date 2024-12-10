package co.getaim.storage.persistence.mysql.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(

    id: Long = 0L
) : BaseEntity(id)

package co.getaim.storage.persistence.mysql.entity.user

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class UserInformation(
    @Column(nullable = false, length = 30)
    val userId: String,

    @Column(nullable = false, length = 30)
    val username: String,
)
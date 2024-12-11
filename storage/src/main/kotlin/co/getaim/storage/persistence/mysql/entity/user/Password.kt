package co.getaim.storage.persistence.mysql.entity.user

import co.getaim.common.utils.sha256Encrypt

data class Password(
    var value: String
) {
    init {
        value = sha256Encrypt(value)
    }
}

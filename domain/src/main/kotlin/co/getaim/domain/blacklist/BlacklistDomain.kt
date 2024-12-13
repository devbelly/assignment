package co.getaim.domain.blacklist

data class BlacklistDomain(
    val id: Long,
    val invalidRefreshToken: String
)

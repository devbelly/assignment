package co.getaim.domain.user

data class UserDomain(
    val id: Long,
    val userId: String,
    val username: String,
    val password: String,
){
    fun authenticate(password: String): Boolean {
        return this.password == password
    }
}
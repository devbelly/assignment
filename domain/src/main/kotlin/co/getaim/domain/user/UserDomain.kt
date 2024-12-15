package co.getaim.domain.user

data class UserDomain(
    val id: Long,
    val userId: String,
    val username: String,
    val password: String,
    val isAdmin: Boolean,
){
    fun authenticate(password: String): Boolean {
        return this.password == password
    }
}
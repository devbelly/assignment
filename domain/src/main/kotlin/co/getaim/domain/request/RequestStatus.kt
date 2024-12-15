package co.getaim.domain.request

enum class RequestStatus(
    val description: String
) {
    REGISTERED("대기"),
    COMPLETED("완료"),
    REJECTED("거절"),
}
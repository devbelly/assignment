package co.getaim.exception

abstract class BaseException : RuntimeException() {
    abstract fun exceptionType(): BaseExceptionType
}
package studio.seno.singlelife

abstract class Result<T> private constructor() {
    class Success<T>(var data: T) : Result<T>()
    class Error<T>(var exception: Exception) : Result<T>()
}
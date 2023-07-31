package br.com.core

sealed class Resource<T> (val data: T? = null,
                         val error: Throwable? = null) {
    class Success<T> (data: T? = null) : Resource<T>(data)
    class Loading<T> (data: T? = null) : Resource<T> (data)
    class Failure<T>(val message: Throwable?, data: T? = null) : Resource<T>(data,message)
}

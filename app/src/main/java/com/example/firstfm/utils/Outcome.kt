package com.example.firstfm.utils


data class Outcome<out T>(val status: Status, val data: T?, val failure: Throwable?) {

    companion object {

        fun <T> success(data: T): Outcome<T> {
            return Outcome(Status.SUCCESS, data, null)
        }

        fun <T> error(failure: Throwable): Outcome<T> {
            return Outcome(Status.ERROR, null, failure)
        }

        fun <T> loading(): Outcome<T> {
            return Outcome(Status.LOADING, null, null)
        }

    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

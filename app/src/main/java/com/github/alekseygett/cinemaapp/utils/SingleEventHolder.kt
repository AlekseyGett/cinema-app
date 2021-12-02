package com.github.alekseygett.cinemaapp.utils

class SingleEventHolder<T>(private val event: T) {

    private var isHandled: Boolean = false

    fun getUnhandledEventOrNull(): T? {
        if (isHandled) {
            return null
        }

        isHandled = true
        return event
    }

}
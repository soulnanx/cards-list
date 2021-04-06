package com.hivecode.hearthstonecards.base

import kotlin.reflect.KClass

sealed class ViewEvents<out T : Any>

data class SuccessRequest<out T : Any>(val data: T, val dataType: KClass<*> = Any::class) : ViewEvents<T>()
data class ErrorRequest(val data: Throwable) : ViewEvents<Nothing>()
data class LoadingRequest(var data: Boolean = false) : ViewEvents<Nothing>() {
    companion object {
        val show = LoadingRequest(true)
        val dismiss = LoadingRequest(false)
    }
}
package com.hivecode.common.mapper

interface DataMapper<T, R> {

    fun from(it: T): R
}
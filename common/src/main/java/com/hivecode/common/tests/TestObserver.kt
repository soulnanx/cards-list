package com.hivecode.common.tests

import androidx.lifecycle.Observer
import java.util.*

class TestObserver<T> : Observer<T> {

    val values = mutableListOf<T>()

    override fun onChanged(value: T) {
        values.add(value)
    }

    fun lastValue() = values.last()

    fun count() = values.size
}
package com.hivecode.data.model

import java.io.Serializable

open class CardTypeInfo (
    val title: String,
    val types: List<String>
) : Serializable
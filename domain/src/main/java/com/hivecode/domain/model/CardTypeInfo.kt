package com.hivecode.domain.model

import java.io.Serializable

open class CardTypeInfo (
    val title: String,
    val types: List<String>
) : Serializable
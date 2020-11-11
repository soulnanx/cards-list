package com.hivecode.data.service.response


import com.google.gson.annotations.SerializedName

data class CardTypeResponse(
    @SerializedName("classes")
    val classes: List<String>?,
    @SerializedName("races")
    val races: List<String>?,
    @SerializedName("types")
    val types: List<String>?
)
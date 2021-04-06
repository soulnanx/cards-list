package com.hivecode.data.service.response

import com.google.gson.annotations.SerializedName

data class GitRepoResponse(
    @SerializedName("full_name") val fullName: String,
    @SerializedName("private") val isPrivate: Boolean
)
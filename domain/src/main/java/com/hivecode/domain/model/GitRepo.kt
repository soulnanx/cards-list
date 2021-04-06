package com.hivecode.domain.model

data class GitRepo(
    val fullName: String,
    private val isPrivate: Boolean
) {
  fun privateLabel() =
      if (isPrivate){
          "Privado"
      } else {
          "Público"
      }
}
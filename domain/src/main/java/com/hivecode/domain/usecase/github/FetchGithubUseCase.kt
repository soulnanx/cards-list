package com.hivecode.domain.usecase.github

import com.hivecode.domain.model.GitRepo
import io.reactivex.Single

interface FetchGithubUseCase {

    fun invoke(username: String): Single<List<GitRepo>>
}
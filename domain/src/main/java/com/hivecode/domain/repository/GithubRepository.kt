package com.hivecode.domain.repository

import com.hivecode.domain.model.GitRepo
import com.hivecode.domain.model.Result
import io.reactivex.Single

interface GithubRepository {

    fun fetchGithubRepo(username: String): Single<List<GitRepo>>
}
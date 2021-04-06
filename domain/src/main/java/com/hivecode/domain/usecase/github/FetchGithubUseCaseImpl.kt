package com.hivecode.domain.usecase.github

import com.hivecode.domain.model.GitRepo
import com.hivecode.domain.model.Result
import com.hivecode.domain.repository.GithubRepository
import io.reactivex.Single

class FetchGithubUseCaseImpl(val repository: GithubRepository): FetchGithubUseCase {
    override fun invoke(username: String): Single<List<GitRepo>> {
        return repository.fetchGithubRepo(username)
    }
}
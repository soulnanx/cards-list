package com.hivecode.data.repository

import com.hivecode.data.mapper.GitReposMapper
import com.hivecode.data.service.GithubService
import com.hivecode.domain.model.GitRepo
import com.hivecode.domain.repository.GithubRepository
import io.reactivex.Single

class GithubRepositoryImpl(
    private val service: GithubService,
    private val mapper: GitReposMapper
) : GithubRepository {
    override fun fetchGithubRepo(username: String): Single<List<GitRepo>> {
        return service
            .fetchGitRepos(username)
            .map { mapper.from(it) }
    }

}
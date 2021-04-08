package com.hivecode.data.repository

import com.hivecode.data.mapper.GitReposMapper
import com.hivecode.data.service.GithubService
import com.hivecode.domain.model.GitRepo
import com.hivecode.domain.repository.GithubRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class GithubRepositoryImpl(
    private val service: GithubService,
    private val mapper: GitReposMapper,
    private val ioScheduler: Scheduler,
    private val computationScheduler: Scheduler
) : GithubRepository {
    override fun fetchGithubRepo(username: String): Single<List<GitRepo>> {
        return service
            .fetchGitRepos(username)
            .subscribeOn(ioScheduler)
            .observeOn(computationScheduler)
            .map {
                mapper.from(it)
            }
    }


}
package com.hivecode.data.service

import com.hivecode.data.BuildConfig
import com.hivecode.data.retrofit.rest.GitRest
import com.hivecode.data.service.config.ApiConnection
import com.hivecode.data.service.response.GitRepoResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubService {
    private val api = ApiConnection().create(BuildConfig.GITHUB_ENDPOINT, GitRest::class.java)

    fun fetchGitRepos(username: String): Single<List<GitRepoResponse>> {
        return api
            .fetchGitRepos(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
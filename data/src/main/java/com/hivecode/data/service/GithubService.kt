package com.hivecode.data.service

import com.hivecode.data.BuildConfig
import com.hivecode.data.retrofit.rest.GitRest
import com.hivecode.data.service.config.ApiConnection

class GithubService {
    private val api = ApiConnection().create(BuildConfig.GITHUB_ENDPOINT, GitRest::class.java)

    fun fetchGitRepos(username: String) = api.fetchGitRepos(username)

}
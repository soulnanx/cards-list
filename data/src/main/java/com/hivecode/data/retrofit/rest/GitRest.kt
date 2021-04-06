package com.hivecode.data.retrofit.rest

import com.hivecode.data.service.response.GitRepoResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitRest {

    @GET("users/{username}/repos")
    fun fetchGitRepos(
        @Path("username") username: String
    ) : Single<List<GitRepoResponse>>
}
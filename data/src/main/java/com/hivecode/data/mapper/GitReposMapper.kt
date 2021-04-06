package com.hivecode.data.mapper

import com.hivecode.common.mapper.DataMapper
import com.hivecode.data.service.response.GitRepoResponse
import com.hivecode.domain.model.GitRepo

class GitReposMapper: DataMapper< List<GitRepoResponse>, List<GitRepo>> {
    override fun from(it: List<GitRepoResponse>) =
        it.map {
            GitRepo(
                fullName = it.fullName,
                isPrivate = it.isPrivate
            )
        }
}
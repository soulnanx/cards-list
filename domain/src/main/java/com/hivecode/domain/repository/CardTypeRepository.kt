package com.hivecode.domain.repository

import com.hivecode.domain.model.CardTypeInfo
import com.hivecode.domain.model.GitRepo
import com.hivecode.domain.model.Result
import io.reactivex.Single

interface CardTypeRepository {

    fun fetchCardType(): Single<List<CardTypeInfo>>
}
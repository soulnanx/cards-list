package com.hivecode.domain.base

import com.hivecode.domain.model.Result

interface BaseUseCase<T : Any, R: Any> {
  suspend operator fun invoke(param: T): Result<R>
}
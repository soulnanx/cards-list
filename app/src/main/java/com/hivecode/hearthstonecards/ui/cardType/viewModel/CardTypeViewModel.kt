package com.hivecode.hearthstonecards.ui.cardType.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hivecode.domain.model.CardTypeInfo
import com.hivecode.domain.model.GitRepo
import com.hivecode.domain.usecase.fetchCardType.FetchCardTypeUseCase
import io.reactivex.disposables.CompositeDisposable

class CardTypeViewModel(
    private val fetchCardTypeUseCase: FetchCardTypeUseCase
) : ViewModel() {

    val disposable = CompositeDisposable()

    val success = MutableLiveData<List<CardTypeInfo>>()
    val failure = MutableLiveData<Throwable>()
    val loading = MutableLiveData<Boolean>()

    init {
        performFetchCardTypeUseCase()
    }

    fun performFetchCardTypeUseCase() {

        val dispose = fetchCardTypeUseCase.invoke()
            .doOnSubscribe {
                loading.value = true
            }
            .doAfterTerminate{
                loading.value = false
            }
            .subscribe(
                {
                    success.value = it
                },{
                    errorHandler(it)
                }
            )

        disposable.add(dispose)
    }

    private fun errorHandler(error: Throwable) {
        failure.value = error
    }

}
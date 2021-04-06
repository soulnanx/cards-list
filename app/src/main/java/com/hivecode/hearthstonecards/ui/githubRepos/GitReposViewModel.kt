package com.hivecode.hearthstonecards.ui.githubRepos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hivecode.domain.model.GitRepo
import com.hivecode.domain.usecase.github.FetchGithubUseCase
import com.hivecode.hearthstonecards.ui.components.ValidationModel
import io.reactivex.disposables.CompositeDisposable

class GitReposViewModel(
    private val fetchGithubUseCase: FetchGithubUseCase
): ViewModel() {

    val disposable = CompositeDisposable()

    val success = MutableLiveData<List<GitRepo>>()
    val failure = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    var username = ValidationModel("", null)
    val usernameBinding = MutableLiveData<String>()


    fun validateAndFetchGitRepos(){
        if (isValidForm()){
            fetchGithubRepo()
        }
    }

    private fun isValidForm() : Boolean{
        val error = this.username.error
        return error == null
    }

    fun changeUsername(value: String) {
        username = if (value.isEmpty()){
            ValidationModel("", "preencha o usuario")
        } else {
            ValidationModel(value, null)
        }
    }

    private fun fetchGithubRepo() {
        val username = this.username.value as String

        val dispose = fetchGithubUseCase.invoke(username)
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
        failure.value = error.toString()
    }

}
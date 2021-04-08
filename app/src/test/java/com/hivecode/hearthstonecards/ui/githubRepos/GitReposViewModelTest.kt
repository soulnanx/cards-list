package com.hivecode.hearthstonecards.ui.githubRepos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hivecode.domain.model.GitRepo
import com.hivecode.domain.usecase.github.FetchGithubUseCase
import com.hivecode.hearthstonecards.ui.components.ValidationModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GitReposViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val useCase = mockk<FetchGithubUseCase>()
    private val success = mockk<Observer<List<GitRepo>>>(relaxed = true)
    private val failure = mockk<Observer<Throwable>>(relaxed = true)
    private lateinit var viewModel: GitReposViewModel
    private val mockedResult = listOf(GitRepo("teste", false))
    private val mockedError = Throwable("error")

    private fun instantiateViewModel(): GitReposViewModel {
        val viewModel = GitReposViewModel(useCase)
        viewModel.success.observeForever(success)
        viewModel.failure.observeForever(failure)
        return viewModel
    }

    @Before
    fun setUp() {
        this.viewModel = instantiateViewModel()
        this.viewModel.username = ValidationModel("teste", null)
    }

    @Test
    fun `when user fetches data then its should call the use case` () {
        val result: Single<List<GitRepo>> = Single.just(mockedResult)

        every { useCase.invoke("teste") } returns result

        viewModel.performFetchGithubRepo()

        verify { useCase.invoke("teste") }
    }

    @Test
    fun `when user fetches data then its returns success` () {
        val result: Single<List<GitRepo>> = Single.just(mockedResult)
        every { useCase.invoke("teste") } returns result

        viewModel.performFetchGithubRepo()

        verify { useCase.invoke("teste") }
        verify { success.onChanged(mockedResult) }
    }

    @Test
    fun `when user fetches data then its returns failure` () {
        every { useCase.invoke("teste") } returns Single.error(mockedError)

        viewModel.performFetchGithubRepo()

        verify { failure.onChanged(mockedError) }
    }

}
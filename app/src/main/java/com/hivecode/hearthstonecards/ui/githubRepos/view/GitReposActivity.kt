package com.hivecode.hearthstonecards.ui.githubRepos.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hivecode.hearthstonecards.R
import com.hivecode.hearthstonecards.base.BaseActivity
import com.hivecode.hearthstonecards.databinding.ReposActivityBinding
import com.hivecode.hearthstonecards.ui.githubRepos.viewModel.GitReposViewModel
import org.koin.android.ext.android.inject

class GitReposActivity: BaseActivity() {

    private val viewModel: GitReposViewModel by inject()

    companion object {
        fun createIntent(
            context: Context
        ): Intent {
            return Intent(context, GitReposActivity::class.java)
        }
    }

    lateinit var binding: ReposActivityBinding

    private fun loadView() {
        this.binding = DataBindingUtil.setContentView<ReposActivityBinding>(
            this,
            R.layout.repos_activity
        ).apply {
            this.lifecycleOwner = this@GitReposActivity
        } as ReposActivityBinding
        this.binding.viewModel = viewModel
    }

    private val adapter = GitReposAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setObservables()
    }

    private fun setObservables() {
        viewModel.usernameBinding.observe(
            this,
            Observer {
                viewModel.changeUsername(it)
                binding.usernameField.error = viewModel.username.error
            }
        )

        viewModel.success.observe(
            this,
            Observer {
                adapter.setList(it)
            }
        )

        viewModel.loading.observe(
            this,
            Observer {
                shouldShowLoading(it)
            }
        )
    }

    private fun init() {
        loadView()
        setView()
    }

    private fun shouldShowLoading(shouldShow: Boolean){
        if (shouldShow) showLoading()
        else hideLoading()
    }

    private fun setView() {
        binding.reposList.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposable.clear()
    }
}
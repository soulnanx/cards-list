package com.hivecode.hearthstonecards.ui.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hivecode.hearthstonecards.R
import com.hivecode.hearthstonecards.base.BaseActivity
import com.hivecode.hearthstonecards.base.Navigation
import com.hivecode.hearthstonecards.databinding.MenuActivityBinding
import com.hivecode.hearthstonecards.ui.cardType.view.CardTypeActivity
import com.hivecode.hearthstonecards.ui.githubRepos.view.GitReposActivity
import org.koin.android.ext.android.inject

class MenuActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private val viewModel: MenuViewModel by inject()

    companion object {
        fun createIntent(
            context: Context
        ): Intent {
            return Intent(context, MenuActivity::class.java)
        }
    }

    lateinit var binding: MenuActivityBinding

    private fun loadView() {
        this.binding = DataBindingUtil.setContentView<MenuActivityBinding>(
            this,
            R.layout.menu_activity
        ).apply {
            this.lifecycleOwner = this@MenuActivity
        } as MenuActivityBinding
        this.binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        loadView()
        setView()
        setObservables()
    }

    private fun setObservables() {
        binding.viewModel?.navigation?.observe(
            this,
            Observer { handleEvent(it) }
        )
    }

    private fun handleEvent(navigation: Navigation) {
        val intent = when(navigation.route){
            "Repos" -> GitReposActivity.createIntent(context = this)
            "Cards" -> CardTypeActivity.createIntent(context = this)
            else -> null
        }

        if (intent != null){
            startActivity(intent)
        }
    }

    private fun setView() {
        binding.menuItem.onItemSelectedListener = this
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(this@MenuActivity, "Selecione uma feature para começar", Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val item = resources.getStringArray(R.array.features_list)
        binding.viewModel?.selectedItem = item[position]
    }

}
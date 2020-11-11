package com.hivecode.hearthstonecards.ui.cardType.view

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hivecode.data.model.CardTypeInfo
import com.hivecode.hearthstonecards.R
import com.hivecode.hearthstonecards.base.BaseActivity
import com.hivecode.hearthstonecards.databinding.CardTypeActivityBinding
import com.hivecode.hearthstonecards.ui.cardList.view.CardListActivity
import com.hivecode.hearthstonecards.ui.cardType.viewModel.CardTypeViewModel
import org.koin.android.ext.android.inject

class CardTypeActivity : BaseActivity() {

    private val viewModel: CardTypeViewModel by inject()
    private val adapter = CardTypeAdapter(::onClickCardType)

    private val binding: CardTypeActivityBinding by lazy {
        DataBindingUtil.setContentView<CardTypeActivityBinding>(
            this,
            R.layout.card_type_activity
        ).apply {
            this.lifecycleOwner = this@CardTypeActivity
        } as CardTypeActivityBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setObservables()
        setView()
    }

    private fun setView() {
        binding.cardTypeRV.adapter = adapter
    }

    private fun setObservables() {

        viewModel.loading.observe(
            this,
            Observer { shouldShowLoading(it) }
        )

        viewModel.cardTypeInfoResult.observe(
            this,
            Observer { addItemsOnRecyclerView(it) }
        )

        viewModel.errorResult.observe(
            this,
            Observer { showError(it) }
        )

    }

    private fun shouldShowLoading(shouldShow: Boolean){
        if (shouldShow) showLoading()
        else hideLoading()
    }

    private fun addItemsOnRecyclerView(cardTypeInfoList: List<CardTypeInfo>){
        adapter.setList(cardTypeInfoList)
    }

    private fun showError(throwable: Throwable){
        Toast.makeText(
            this,
            throwable.message ?: "erro desconhecido",
            Toast.LENGTH_SHORT)
        .show()
    }

    private fun onClickCardType(cardTypeInfo: CardTypeInfo, selectedType: String){
        val intent = CardListActivity.createIntent(
            this@CardTypeActivity,
            cardTypeInfo,
            selectedType
        )
        startActivity(intent)
    }

}
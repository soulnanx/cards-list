package com.hivecode.hearthstonecards.ui.cardList.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hivecode.domain.model.Card
import com.hivecode.domain.model.CardTypeInfo
import com.hivecode.hearthstonecards.R
import com.hivecode.hearthstonecards.base.BaseActivity
import com.hivecode.hearthstonecards.databinding.CardListActivityBinding
import com.hivecode.hearthstonecards.ui.cardList.viewModel.CardListViewModel
import org.koin.android.ext.android.inject
import retrofit2.HttpException


class CardListActivity : BaseActivity() {

    private val viewModel: CardListViewModel by inject()
    private val adapter = CardListAdapter(::onClickCard)

    companion object {
        fun createIntent(
            context: Context,
            cardTypeInfo: CardTypeInfo,
            selectedType: String
        ) = Intent(context, CardListActivity::class.java).apply {
            putExtra(cardTypeParam, cardTypeInfo)
            putExtra(selectedTypeParam, selectedType)
        }

        const val cardTypeParam = "cardTypeParam"
        const val selectedTypeParam = "selectedTypeParam"
    }

    private val cardTypeInfo: CardTypeInfo by lazy {
        intent.extras?.getSerializable(cardTypeParam) as CardTypeInfo
    }

    private val selectedType: String by lazy {
        intent.extras?.getSerializable(selectedTypeParam) as String
    }

    private val binding: CardListActivityBinding by lazy {
        DataBindingUtil.setContentView<CardListActivityBinding>(
            this,
            R.layout.card_list_activity
        ).apply {
            this.lifecycleOwner = this@CardListActivity
        } as CardListActivityBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setObservables()
        setView()
    }

    private fun setView() {
        binding.cardRV.adapter = adapter
        binding.activity = this
        binding.selectedType = selectedType
        viewModel.fetchCardByCardTypeInfo(cardTypeInfo, selectedType)
    }

    private fun setObservables() {

        viewModel.loading.observe(
            this,
            Observer { shouldShowLoading(it) }
        )

        viewModel.success.observe(
            this,
            Observer { addCardsOnRecyclerView(it) }
        )

        viewModel.failure.observe(
            this,
            Observer { showError(it) }
        )

    }

    private fun shouldShowLoading(shouldShow: Boolean){
        if (shouldShow) showLoading()
        else hideLoading()
    }

    private fun addCardsOnRecyclerView(cardList: List<Card>){
        binding.cardRV.visibility = View.VISIBLE
        adapter.setList(cardList)
    }

    private fun showError(throwable: Throwable){
        if (throwable is HttpException) {
            when (throwable.code()){
                404 -> showNoCard()
                else -> showDefaultError()
            }
        } else {
            showDefaultError()
        }
    }

    private fun showDefaultError() {
        binding.messageContainer.visibility = View.VISIBLE
        binding.message.text = getString(R.string.default_internal_error)
    }

    private fun showNoCard() {
        binding.messageContainer.visibility = View.VISIBLE
        binding.message.text = getString(R.string.empty_cards_error)
    }

    private fun onClickCard(){

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposable.clear()
    }

}
package com.hivecode.hearthstonecards.ui.cardList.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hivecode.data.model.Card
import com.hivecode.data.model.CardTypeInfo
import com.hivecode.hearthstonecards.R
import com.hivecode.hearthstonecards.databinding.CardListActivityBinding
import com.hivecode.hearthstonecards.ui.cardList.viewModel.CardListViewModel
import org.koin.android.ext.android.inject


class CardListActivity : AppCompatActivity() {

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
        viewModel.fetchCardByType(cardTypeInfo, selectedType)
    }

    private fun setObservables() {

        viewModel.loading.observe(
            this,
            Observer { shouldShowLoading(it) }
        )

        viewModel.cardResult.observe(
            this,
            Observer {
                addCardsOnRecyclerView(it) }
        )

        viewModel.errorResult.observe(
            this,
            Observer {
                showError(it) }
        )

    }

    private fun shouldShowLoading(shouldShow: Boolean){
        // showLoading
    }

    private fun addCardsOnRecyclerView(cardList: List<Card>){
        adapter.setList(cardList)
    }

    private fun showError(throwable: Throwable){
        Toast.makeText(
            this,
            throwable.message ?: "erro desconhecido",
            Toast.LENGTH_SHORT)
        .show()
    }

    private fun onClickCard(){
//        viewModel.fetchCardsByType(text)
    }

}
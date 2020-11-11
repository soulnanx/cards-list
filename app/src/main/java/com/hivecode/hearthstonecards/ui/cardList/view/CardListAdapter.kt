package com.hivecode.hearthstonecards.ui.cardList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hivecode.data.model.Card
import com.hivecode.hearthstonecards.databinding.ItemCardBinding
import com.hivecode.hearthstonecards.extensions.loadUrl

class CardListAdapter(
    private val onClick: () -> Unit
) : RecyclerView.Adapter<CardListAdapter.ViewHolder>() {

    private val cardList : MutableList<Card> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cardList[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun setList(cardTypeInfoList : List<Card>){
        this.cardList.clear()
        this.cardList.addAll(cardTypeInfoList)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemCardBinding,
        private val onClick: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            if (card.img.isNotEmpty()){
                binding.cardIV.loadUrl(card.img)
            }

        }
    }
}
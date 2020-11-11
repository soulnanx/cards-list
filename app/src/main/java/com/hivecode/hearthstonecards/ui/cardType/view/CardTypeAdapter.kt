package com.hivecode.hearthstonecards.ui.cardType.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hivecode.data.model.CardTypeInfo
import com.hivecode.hearthstonecards.databinding.ItemCardTypeBodyBinding

class CardTypeAdapter(
    private val onClick: (CardTypeInfo, String) -> Unit
) : RecyclerView.Adapter<CardTypeAdapter.ViewHolder>() {

    private val cardTypeInfoList : MutableList<CardTypeInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCardTypeBodyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardTypeInfo = cardTypeInfoList[position]
        holder.bind(cardTypeInfo)
    }

    override fun getItemCount(): Int {
        return cardTypeInfoList.size
    }

    fun setList(cardTypeInfoList : List<CardTypeInfo>){
        this.cardTypeInfoList.clear()
        this.cardTypeInfoList.addAll(cardTypeInfoList)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemCardTypeBodyBinding,
        private val onClick: (CardTypeInfo, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cardTypeInfo: CardTypeInfo) {
            binding.cardTypeInfo = cardTypeInfo
            binding.itemCartTypeLineRV.adapter = ItemCardTypeAdapter(cardTypeInfo, onClick)
        }
    }
}
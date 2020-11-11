package com.hivecode.hearthstonecards.ui.cardType.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hivecode.data.model.CardTypeInfo
import com.hivecode.hearthstonecards.databinding.ItemCardTypeCardBinding
import java.util.*

class ItemCardTypeAdapter(
    private val cardTypeInfo: CardTypeInfo,
    private val onClick: (CardTypeInfo, String) -> Unit
) : RecyclerView.Adapter<ItemCardTypeAdapter.ViewHolder>() {

    private val cardTypeTitleList: List<String> = cardTypeInfo.types

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCardTypeCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cardTypeTitleList[position]
        holder.bind(cardTypeInfo, item)
    }

    override fun getItemCount(): Int {
        return cardTypeTitleList.size
    }

    class ViewHolder(
        private val binding: ItemCardTypeCardBinding,
        private val onClick: (CardTypeInfo, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cardTypeInfo: CardTypeInfo, title: String) {
            binding.cardTypeTitleTextView.text = title
            binding.cardTypeCardView.setCardBackgroundColor(getBackgroundColor())
            binding.cardTypeCardView.setOnClickListener {
                onClick(cardTypeInfo, title)
            }
        }

        private fun getBackgroundColor(): Int {
            val rnd = Random()
            return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        }
    }


}
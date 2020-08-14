package com.nullit.test_task.ui.fragments.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nullit.test_task.R
import com.nullit.test_task.ui.api.dto.BestBookDto
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.vertical_book_list_item.view.*

class VerticalRecyclerAdapter(
    private val bestItemClickListener: BestItemClickListener? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var bestBooks: List<BestBookDto> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BestBoolViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_book_list_item, parent, false),
            bestItemClickListener
        )
    }

    override fun getItemCount(): Int = bestBooks.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BestBoolViewHolder) {
            holder.bind(bestBooks[position])
        }
    }

    class BestBoolViewHolder(
        view: View,
        private val bestItemClickListener: BestItemClickListener?
    ) : RecyclerView.ViewHolder(view) {
        fun bind(bestBook: BestBookDto) = with(itemView) {
            setOnClickListener {
                bestItemClickListener?.onItemClick(bestBook)
            }
            Glide.with(itemView)
                .load(bestBook.image)
                .centerCrop()
                .into(logoImageView)
            nameOfBookTextView.text = bestBook.title
            authorTextView.text = bestBook.author
            priceTextView.text = bestBook.price.toString() + "€" // да можно через стринг, но так быстрее
            val text = "<font color=#ffffff>${bestBook.rate.score}</font> <font color=#707070>(${bestBook.rate.amount})</font>"
            ratingTextView.setText(Html.fromHtml(text, 0))
        }
    }
}

interface BestItemClickListener {
    fun onItemClick(bestBook: BestBookDto)
}
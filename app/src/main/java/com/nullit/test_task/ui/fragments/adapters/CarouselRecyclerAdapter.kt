package com.nullit.test_task.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nullit.test_task.R
import com.nullit.test_task.ui.api.dto.MainCarouselDtoItem
import kotlinx.android.synthetic.main.carousel_scalable_item.view.*

class CarouselRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var carouselItems: List<MainCarouselDtoItem> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CarouselViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.carousel_scalable_item, parent, false)
        )
    }

    override fun getItemCount(): Int = carouselItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CarouselViewHolder) {
            holder.bind(carouselItems[position])
        }
    }

    class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(mainCarouselDtoItem: MainCarouselDtoItem) = with(itemView) {
            Glide.with(itemView)
                .load(mainCarouselDtoItem.imageUrl)
                .centerCrop()
                .into(image)
        }
    }
}
package com.nature_farm.android.homepage.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nature_farm.android.homepage.databinding.ItemImageSliderBinding
import com.nature_farm.android.homepage.model.SliderImage

class ImageSliderAdapter :
    androidx.recyclerview.widget.ListAdapter<SliderImage, ImageSliderAdapter.ViewHolder>(
        DiffCallback()
    ) {
    class DiffCallback : DiffUtil.ItemCallback<SliderImage>() {
        override fun areItemsTheSame(oldItem: SliderImage, newItem: SliderImage): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SliderImage, newItem: SliderImage): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(val binding: ItemImageSliderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: SliderImage) {
            Glide.with(binding.root).load(item.image).centerCrop().into(binding.ivCarousel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewHolder(
            ItemImageSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageItem = getItem(position)
        holder.bindData(imageItem)
    }
}
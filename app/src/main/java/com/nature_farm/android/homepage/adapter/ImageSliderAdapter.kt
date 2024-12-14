package com.nature_farm.android.homepage.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nature_farm.android.homepage.databinding.ItemImageSliderBinding
import com.nature_farm.android.homepage.core.data.domain.model.SliderImage

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


                binding.ivImageSlider.setImageResource(item.image )

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
package com.nature_farm.android.homepage.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nature_farm.android.homepage.databinding.ItemExclusiveBrandBinding
import com.nature_farm.android.homepage.model.ExclusiveBrand

class ExclusiveBrandAdapter(private val exclusiveBrands: ArrayList<ExclusiveBrand>) :
    RecyclerView.Adapter<ExclusiveBrandAdapter.ExclusiveBrandViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ExclusiveBrandAdapter.ExclusiveBrandViewHolder {
        return ExclusiveBrandViewHolder(
            ItemExclusiveBrandBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ExclusiveBrandAdapter.ExclusiveBrandViewHolder,
        position: Int,
    ) {
        val data = exclusiveBrands[position]
        holder.binding.logoBrand1.setImageResource(data.imageOne)
        holder.binding.logoBrand2.setImageResource(data.imageTwo)
    }

    override fun getItemCount(): Int {
        return exclusiveBrands.size
    }

    inner class ExclusiveBrandViewHolder(var binding: ItemExclusiveBrandBinding) :
        ViewHolder(binding.root)
}
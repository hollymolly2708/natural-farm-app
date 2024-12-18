package com.natural_farm.android.e_commerce.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.natural_farm.android.e_commerce.databinding.ItemHealthConditionBinding
import com.natural_farm.android.e_commerce.core.data.domain.model.HealthCondition

class HealthConditionAdapter(private val healthConditions: ArrayList<HealthCondition>) :
    RecyclerView.Adapter<HealthConditionAdapter.HealthConditionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HealthConditionAdapter.HealthConditionViewHolder {
        return HealthConditionViewHolder(
            ItemHealthConditionBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: HealthConditionAdapter.HealthConditionViewHolder,
        position: Int,
    ) {
        val data = healthConditions[position]
        holder.binding.ivHealthCondition.setImageResource(data.image)
        holder.binding.tvHealthCondition.text = data.name
    }

    override fun getItemCount(): Int {
        return healthConditions.size
    }

    inner class HealthConditionViewHolder(var binding: ItemHealthConditionBinding) :
        ViewHolder(binding.root)
}
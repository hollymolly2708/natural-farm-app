package com.nature_farm.android.homepage.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nature_farm.android.homepage.databinding.ItemCategoryBinding
import com.nature_farm.android.homepage.model.Category

class CategoryAdapter(private val categories: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CategoryAdapter.CategoryViewHolder {
        val view = CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return view
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        val data = categories[position]
        holder.binding.tvOnSale.text = data.categoryName
        holder.binding.ivOnSale.setImageResource(data.image)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class CategoryViewHolder(var binding: ItemCategoryBinding) : ViewHolder(binding.root)
}
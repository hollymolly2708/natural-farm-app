package com.nature_farm.android.homepage.ui.main.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nature_farm.android.homepage.databinding.ItemProductShimmerBinding

class ProductShimmerAdapter :
    RecyclerView.Adapter<ProductShimmerAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProductShimmerAdapter.ProductViewHolder {
        val view =
            ItemProductShimmerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(
        holder: ProductShimmerAdapter.ProductViewHolder,
        position: Int,
    ) {

    }

    inner class ProductViewHolder(var binding: ItemProductShimmerBinding) :
        RecyclerView.ViewHolder(binding.root)

}
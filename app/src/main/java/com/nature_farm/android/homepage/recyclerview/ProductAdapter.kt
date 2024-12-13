package com.nature_farm.android.homepage.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nature_farm.android.homepage.databinding.ItemProductBinding
import com.nature_farm.android.homepage.model.Product

class ProductAdapter(private val products: ArrayList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProductAdapter.ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        val data = products[position]
        Glide.with(holder.binding.root).load(data.productImage).into(holder.binding.ivProduct)
        holder.binding.tvInputProductName.text = data.productName
        holder.binding.tvInputProductBrand.text = data.brandName
        holder.binding.tvInputStar.text = data.rating
        holder.binding.tvInputPriceOriginal.text = data.priceOriginal
        holder.binding.tvInputPriceAfterDiscount.text = data.priceAfterDiscount
        holder.binding.tvInputDiscount.text = data.discount
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ProductViewHolder(var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)
}
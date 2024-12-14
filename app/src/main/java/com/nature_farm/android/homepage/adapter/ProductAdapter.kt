package com.nature_farm.android.homepage.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nature_farm.android.homepage.databinding.ItemProductBinding
import com.nature_farm.android.homepage.core.data.domain.model.Product

class ProductAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun onItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

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

        if (data.productImage is Int) {
            // Jika productImage adalah resource ID (Int)
            holder.binding.ivProduct.setImageResource(data.productImage)
        } else if (data.productImage is String) {
            // Jika productImage adalah URL (String)
            Glide.with(holder.itemView.context)
                .load(data.productImage as String) // Load URL menggunakan Glide
                .centerCrop()
                .skipMemoryCache(true)
                .into(holder.binding.ivProduct)
        }

//        if(data.ratingCount != null){
//            holder.
//        }

        if (data.discount == null) {
            holder.binding.tvInputPriceOriginal.visibility = View.GONE
            holder.binding.tvInputPriceAfterDiscount.visibility = View.VISIBLE
            holder.binding.tvInputDiscount.visibility = View.GONE
            holder.binding.tvInputPriceAfterDiscount.text = "$ ${data.priceAfterDiscount}"

        } else {
            holder.binding.tvInputDiscount.visibility = View.VISIBLE
            holder.binding.tvInputPriceOriginal.visibility = View.VISIBLE
            holder.binding.tvInputPriceAfterDiscount.visibility = View.VISIBLE
            holder.binding.tvInputPriceAfterDiscount.text = "${data.priceAfterDiscount}"
        }

        if (data.brandName == null) {
            holder.binding.tvInputProductBrandOrCategory.text = data.category

        } else {
            holder.binding.tvInputProductBrandOrCategory.text = data.brandName

        }

        holder.binding.cvItemProduct.setOnClickListener {
            onItemClickCallback?.onClicked(data)
        }
        holder.binding.tvInputProductName.text = data.productName
        holder.binding.tvInputStar.text = data.rating
        holder.binding.tvInputPriceOriginal.text = data.priceOriginal
        holder.binding.tvInputPriceOriginal.paintFlags =
            holder.binding.tvInputPriceOriginal.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        holder.binding.tvInputDiscount.text = data.discount
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateListData(newListData: List<Product>) {
        this.products = newListData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onClicked(data: Product)
    }
}
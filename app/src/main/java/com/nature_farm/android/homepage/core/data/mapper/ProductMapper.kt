package com.nature_farm.android.homepage.core.data.mapper

import com.nature_farm.android.homepage.core.data.domain.model.DetailProduct
import com.nature_farm.android.homepage.core.data.source.remote.response.ProductResponse
import com.nature_farm.android.homepage.core.data.domain.model.Product
import com.nature_farm.android.homepage.core.data.source.remote.response.ProductResponseItem

object ProductMapper {
    fun productResponsesToProducts(productResponses: List<ProductResponseItem?>): List<Product>? {

        val productResponseItems = productResponses
        val products = productResponseItems.map { productResponseItem ->
            val rating = productResponseItem?.rating?.rate
            return@map Product(
                id = productResponseItem?.id,
                productName = productResponseItem?.title,
                category = productResponseItem?.category,
                priceAfterDiscount = productResponseItem?.price.toString(),
                rating = rating.toString(),
                productImage = productResponseItem?.image

            )
        }

        return products
    }


    fun productResponseToDetailProduct(productResponse: ProductResponseItem): DetailProduct {
        return DetailProduct(
            id = productResponse.id,
            productImage = productResponse.image,
            productName = productResponse.title,
            category = productResponse.category,
            ratingCount = productResponse.rating?.count,
            priceAfterDiscount = productResponse.price,
            rating = productResponse.rating?.rate.toString(),
            description = productResponse.description
        )
    }
}
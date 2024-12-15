package com.natural_farm.android.e_commerce.core.data.domain.model

import java.math.BigDecimal

data class DetailProduct(
    val id : Int?  = null,
    val brandName: String? = null,
    val productName: String? = null,
    val discount : String? = null,
    val productImage: String? = null,
    val priceOriginal: String? = null,
    val priceAfterDiscount: BigDecimal? = null,
    val rating : String? = null,
    val ratingCount : Int? = null,
    val category : String? = null,
    val description : String? = null,
    val hasBeenSold : String? = null
)

package com.nature_farm.android.homepage.core.data.domain.model

data class Product(
    val id : Int?  = null,
    val brandName: String? = null,
    val productName: String? = null,
    val discount : String? = null,
    val productImage: Any? = null,
    val priceOriginal: String? = null,
    val priceAfterDiscount: String? = null,
    val rating : String? = null,
    val category : String? = null,
    val description : String? = null,
    val hasBeenSold : String? = null
)

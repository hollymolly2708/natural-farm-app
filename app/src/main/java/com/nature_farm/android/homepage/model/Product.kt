package com.nature_farm.android.homepage.model

data class Product(

    val brandName: String,
    val productName: String,
    val productImage: Int,
    val priceOriginal: String,
    val priceAfterDiscount: String,
    val rating : String,
    val hasBeenSold : String
)

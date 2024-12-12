package com.nature_farm.android.homepage.utils

import com.nature_farm.android.homepage.R
import com.nature_farm.android.homepage.model.Category
import com.nature_farm.android.homepage.model.Product

object Data {
    fun categories(): ArrayList<Category> {
        val category = ArrayList<Category>()
        category.add(Category(R.drawable.ic_discount, "On Sale\nProducts"))
        category.add(Category(R.drawable.ic_mom_baby, "Mom & Baby"))
        category.add(Category(R.drawable.ic_vitamins, "Vitamins &\nSupplements"))
        category.add(Category(R.drawable.ic_personal_care, "Personal Care"))
        category.add(Category(R.drawable.ic_weight_management, "Weight\nManagements"))
        return category
    }

    fun products(): ArrayList<Product> {
        val product = ArrayList<Product>()
        product.add(
            Product(
                "BREYLEE",
                "BREYLEE Aloe Vera Clay Mask 8 Gr",
                R.drawable.ic_product_1,
                "Rp 6.500",
                "Rp 5.850",
                "4,9",
                "Terjual 5rb+"
            )
        )
        product.add(
            Product(
                "BREYLEE",
                "BREYLEE Orange Blossom Clay Mask 8 Gr",
                R.drawable.ic_product_2,
                "Rp 6.500",
                "Rp. 5850",
                "4,9",
                "Terjual 5rb+"
            )
        )

        return product
    }
}
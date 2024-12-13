package com.nature_farm.android.homepage

import com.nature_farm.android.homepage.R
import com.nature_farm.android.homepage.model.Article
import com.nature_farm.android.homepage.model.SliderImage
import com.nature_farm.android.homepage.model.Category
import com.nature_farm.android.homepage.model.ExclusiveBrand
import com.nature_farm.android.homepage.model.HealthCondition
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
                "10%",
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
                "10%",
                R.drawable.ic_product_2,
                "Rp 6.500",
                "Rp. 5.850",
                "4,9",
                "Terjual 5rb+"
            )
        )

        return product
    }

    fun healthConditions(): ArrayList<HealthCondition> {
        val healthCondition = ArrayList<HealthCondition>()
        healthCondition.add(HealthCondition("Kolesterol", R.drawable.ic_kolesterol))
        healthCondition.add(HealthCondition("Darah Tinggi", R.drawable.ic_darah_tinggi))
        healthCondition.add(HealthCondition("Darah Rendah", R.drawable.ic_darah_rendah))
        healthCondition.add(HealthCondition("Lihat Semua", R.drawable.ic_see_all))
        return healthCondition
    }

    fun exclusiveBrand(): ArrayList<ExclusiveBrand> {
        val exclusiveBrand = ArrayList<ExclusiveBrand>()
        exclusiveBrand.add(ExclusiveBrand(R.drawable.ic_on, R.drawable.ic_on_2))
        exclusiveBrand.add(
            ExclusiveBrand(
                R.drawable.ic_manuka_health,
                R.drawable.ic_manuka_health_2
            )
        )
        exclusiveBrand.add(ExclusiveBrand(R.drawable.ic_childlife, R.drawable.ic_childlife_2))
        exclusiveBrand.add(ExclusiveBrand(R.drawable.ic_wellness, R.drawable.ic_wellness_2))
        return exclusiveBrand
    }

    fun article(): ArrayList<Article> {
        val article = ArrayList<Article>()
        article.add(
            Article(
                R.drawable.ic_article_1,
                "Beauty",
                "5 Rekomendasi Hair Tonic untuk Rambut Rontok yang Paling Ampuh",
                "Richard",
                R.drawable.ic_person,
                "18 Jul 2024"
            )
        )

        article.add(
            Article(
                R.drawable.ic_article_2,
                "Health",
                "Enema Kopi yang Mengandung Manfaat dan Efek Sampingnya",
                "Richard",
                R.drawable.ic_person,
                "18 Jul 2024"
            )
        )

        return article
    }

    fun carousel(): ArrayList<SliderImage> {
        val sliderImages = ArrayList<SliderImage>()
        sliderImages.add(SliderImage(1, R.drawable.ic_carousel_1))
        sliderImages.add(SliderImage(2, R.drawable.ic_carousel_2))
        sliderImages.add(SliderImage(3, R.drawable.ic_carousel_3))
        return sliderImages
    }

    fun banner(): ArrayList<SliderImage> {
        val sliderImages = ArrayList<SliderImage>()
        sliderImages.add(SliderImage(1, R.drawable.ic_banner_1))
        sliderImages.add(SliderImage(2, R.drawable.ic_banner_2))
        sliderImages.add(SliderImage(3, R.drawable.ic_banner_3))
        return sliderImages
    }
}
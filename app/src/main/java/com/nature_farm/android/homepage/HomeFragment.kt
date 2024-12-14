package com.nature_farm.android.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.nature_farm.android.homepage.databinding.FragmentHomeBinding
import com.nature_farm.android.homepage.core.data.domain.model.SliderImage
import com.nature_farm.android.homepage.adapter.ArticleAdapter
import com.nature_farm.android.homepage.adapter.ImageSliderAdapter
import com.nature_farm.android.homepage.adapter.CategoryAdapter
import com.nature_farm.android.homepage.adapter.ExclusiveBrandAdapter
import com.nature_farm.android.homepage.adapter.HealthConditionAdapter
import com.nature_farm.android.homepage.adapter.ProductAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var pageChangeListener: ViewPager2.OnPageChangeCallback? = null
    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply { setMargins(3, 0, 3, 0) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCategoryAdapter()
        setFlashSaleRecyclerView()
        setHealthConditionRecyclerView()
        setExclusiveBrandRecyclerView()
        setBestSellerRecyclerview()
        setArticleRecyclerview()
        setupCarousel(Data.carousel())
        setupBanner(Data.banner())
    }

    private fun setCategoryAdapter() {
        val adapter = CategoryAdapter(Data.categories())
        binding.rvCategories.adapter = adapter
        binding.rvCategories.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.setHasFixedSize(true)
    }

    private fun setFlashSaleRecyclerView() {
        val adapter = ProductAdapter(Data.products())
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager =
            GridLayoutManager(requireActivity(),2,GridLayoutManager.VERTICAL,false)
        binding.rvProducts.setHasFixedSize(true)
    }

    private fun setHealthConditionRecyclerView() {
        val adapter = HealthConditionAdapter(Data.healthConditions())
        binding.rvHealthCondition.adapter = adapter
        binding.rvHealthCondition.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHealthCondition.setHasFixedSize(true)
    }

    private fun setExclusiveBrandRecyclerView() {
        val adapter = ExclusiveBrandAdapter(Data.exclusiveBrand())
        binding.rvExclusiveBrand.adapter = adapter
        binding.rvExclusiveBrand.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvExclusiveBrand.setHasFixedSize(true)

    }

    private fun setBestSellerRecyclerview() {
        val adapter = ProductAdapter(Data.products())
        binding.rvBestSellerProducts.adapter = adapter
        binding.rvBestSellerProducts.layoutManager =
            GridLayoutManager(requireActivity(),2, GridLayoutManager.VERTICAL, false)
        binding.rvBestSellerProducts.setHasFixedSize(true)
    }

    private fun setArticleRecyclerview() {
        val adapter = ArticleAdapter(Data.article())
        binding.rvArticle.adapter = adapter
        binding.rvArticle.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvArticle.setHasFixedSize(true)
    }

    private fun setupCarousel(sliderImages: List<SliderImage?>?) {
        val imageAdapter = ImageSliderAdapter()
        binding.vpCarousel.adapter = imageAdapter
        binding.slideDotLL.removeAllViews()

        imageAdapter.submitList(sliderImages)
        sliderImages?.let {
            val dotsImage = Array(it.size) { ImageView(requireActivity()) }
            dotsImage.forEach { dot ->
                dot.setImageResource(R.drawable.non_active_dot)
                binding.slideDotLL.addView(dot, params)
            }

            dotsImage[0].setImageResource(R.drawable.active_dot)
            pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    dotsImage.forEachIndexed { index, imageView ->
                        imageView.setImageResource(if (position == index) R.drawable.active_dot else R.drawable.non_active_dot)
                    }
                }
            }
            binding.vpCarousel.registerOnPageChangeCallback(pageChangeListener!!)
        }
    }

    private fun setupBanner(sliderImages: List<SliderImage?>?) {
        val imageAdapter = ImageSliderAdapter()
        binding.vpBanner.adapter = imageAdapter
        binding.slideDotLL2.removeAllViews()

        imageAdapter.submitList(sliderImages)
        sliderImages?.let {
            val dotsImage = Array(it.size) { ImageView(requireActivity()) }
            dotsImage.forEach { dot ->
                dot.setImageResource(R.drawable.non_active_dot)
                binding.slideDotLL2.addView(dot, params)
            }

            dotsImage[0].setImageResource(R.drawable.active_dot)
            pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    dotsImage.forEachIndexed { index, imageView ->
                        imageView.setImageResource(if (position == index) R.drawable.active_dot else R.drawable.non_active_dot)
                    }
                }
            }
            binding.vpBanner.registerOnPageChangeCallback(pageChangeListener!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pageChangeListener?.let { binding.vpCarousel.unregisterOnPageChangeCallback(it) }
        pageChangeListener?.let { binding.vpBanner.unregisterOnPageChangeCallback(it) }
    }
}
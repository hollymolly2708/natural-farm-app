package com.nature_farm.android.homepage

// FilterFragment.kt
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nature_farm.android.homepage.databinding.FragmentFilterBinding

class FilterFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    var selectedCategory: String? = null
    var selectedLimit: Int? = null
    var selectedSorting: String? = null

    private var listener: FilterListener? = null

    fun setFilterListener(filterListener: FilterListener) {
        listener = filterListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil kategori yang disimpan

        selectedCategory = getSavedCategory()
        selectedLimit = getSavedLimit()
        selectedSorting = getSelectedSort()

        // Tampilkan warna tombol berdasarkan kategori yang disimpan
        bindingViewColors()

        setupFilter()

        binding.btnApply.setOnClickListener {
            selectedCategory?.let { category ->
                saveSelectedCategory(category) // Simpan kategori yang dipilih
                listener?.onCategorySelected(category) // Kirim ke listener
                saveSelectedLimit(0)
                saveSelectedSort(null)
            }

            selectedLimit?.let { limit ->
                saveSelectedLimit(limit)
                listener?.onLimitSelected(limit)
                saveSelectedCategory(null)
                saveSelectedSort(null)
            }

            selectedSorting?.let { sort ->
                saveSelectedSort(sort)
                saveSelectedLimit(0)
                saveSelectedCategory(null)
                listener?.onSortingSelected(sort)
            }

            dismiss()
        }
    }

    private fun bindingViewColors() {
        // Reset semua tombol ke warna default
        resetButtonColors()

        // Atur warna background sesuai kategori yang dipilih
        when (selectedCategory) {
            "electronics" -> {
                binding.btnEletronics.setBackgroundDrawable(
                    ContextCompat.getDrawable(requireActivity(), R.drawable.background_stroke_green)
                )

            }

            "jewelery" -> binding.btnJewelery.setBackgroundDrawable(
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_stroke_green)
            )

            "men's clothing" -> binding.btnMenClothing.setBackgroundDrawable(
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_stroke_green)
            )

            "women's clothing" -> binding.btnWomenClothing.setBackgroundDrawable(
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_stroke_green)
            )
        }

        when (selectedLimit) {
            10 -> binding.btnLimit10.setBackgroundDrawable(
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_stroke_green)
            )

            5 -> binding.btnLimit5.setBackgroundDrawable(
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_stroke_green)
            )
        }
        when (selectedSorting) {
            "asc" -> binding.btnAscending.setBackgroundDrawable(
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_stroke_green)
            )

            "desc" -> binding.btnDescending.setBackgroundDrawable(
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_stroke_green)
            )
        }
    }

    private fun resetButtonColors() {
        val defaultColor = ContextCompat.getColor(requireActivity(), R.color.white)
        binding.btnEletronics.setBackgroundColor(defaultColor)
        binding.btnJewelery.setBackgroundColor(defaultColor)
        binding.btnMenClothing.setBackgroundColor(defaultColor)
        binding.btnWomenClothing.setBackgroundColor(defaultColor)
        binding.btnLimit5.setBackgroundColor(defaultColor)
        binding.btnLimit10.setBackgroundColor(defaultColor)
        binding.btnAscending.setBackgroundColor(defaultColor)
        binding.btnDescending.setBackgroundColor(defaultColor)
    }

    private fun setupFilter() {
        binding.btnEletronics.setOnClickListener {
            selectedLimit = null
            selectedSorting = null
            selectedCategory = "electronics"
            bindingViewColors()

        }
        binding.btnJewelery.setOnClickListener {
            selectedLimit = null
            selectedSorting = null
            selectedCategory = "jewelery"
            bindingViewColors()

        }
        binding.btnMenClothing.setOnClickListener {
            selectedLimit = null
            selectedSorting = null
            selectedCategory = "men's clothing"
            bindingViewColors()

        }
        binding.btnWomenClothing.setOnClickListener {
            selectedLimit = null
            selectedSorting = null
            selectedCategory = "women's clothing"
            bindingViewColors()

        }

        binding.btnLimit10.setOnClickListener {
            selectedCategory = null
            selectedSorting = null
            selectedLimit = 10
            bindingViewColors()

        }
        binding.btnLimit5.setOnClickListener {
            selectedCategory = null
            selectedSorting = null
            selectedLimit = 5
            bindingViewColors()

        }

        binding.btnAscending.setOnClickListener {
            selectedSorting = "asc"
            selectedCategory = null
            selectedLimit = null

            bindingViewColors()

        }
        binding.btnDescending.setOnClickListener {
            selectedSorting = "desc"
            selectedCategory = null
            selectedLimit = null

            bindingViewColors()
        }
    }

    private fun saveSelectedSort(sort: String?) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("selectedSort", sort).apply()
    }

    private fun getSelectedSort(): String? {
        val sharedPreferences =
            requireActivity().getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("selectedSort", null)
    }

    private fun saveSelectedCategory(category: String?) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("selectedCategory", category).apply()
    }

    private fun saveSelectedLimit(limit: Int) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("selectedLimit", limit).apply()
    }

    private fun getSavedLimit(): Int {
        val sharedPreferences =
            requireActivity().getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("selectedLimit", 0)
    }

    private fun getSavedCategory(): String? {
        val sharedPreferences =
            requireActivity().getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("selectedCategory", null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface FilterListener {
        fun onCategorySelected(category: String)
        fun onSortingSelected(sort: String)
        fun onLimitSelected(limit: Int)
    }
}

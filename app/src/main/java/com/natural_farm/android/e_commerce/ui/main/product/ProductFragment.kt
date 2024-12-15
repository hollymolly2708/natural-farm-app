package com.natural_farm.android.e_commerce.ui.main.product

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.natural_farm.android.e_commerce.FilterFragment
import com.natural_farm.android.e_commerce.adapter.ProductAdapter
import com.natural_farm.android.e_commerce.core.data.di.Injector
import com.natural_farm.android.e_commerce.core.data.domain.model.Product
import com.natural_farm.android.e_commerce.databinding.FragmentProductBinding
import com.natural_farm.android.e_commerce.ui.detail.DetailProductActivity
import com.natural_farm.android.e_commerce.utils.SharedPrefHelper.saveSelectedCategory
import com.natural_farm.android.e_commerce.utils.SharedPrefHelper.saveSelectedLimit
import com.natural_farm.android.e_commerce.utils.SharedPrefHelper.saveSelectedSort


class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductBinding.inflate(inflater, container, false)

        saveSelectedCategory(requireActivity(), null)
        saveSelectedLimit(requireActivity(), 0)
        saveSelectedSort(requireActivity(), null)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupObserver()
        getAllProducts()
        setupShimmerProduct()
//
//        if (FilterFragment().isReset == true) {
//            viewModel.getAllProducts()
//            setupLoading(true)
//        }

        binding.ivFilterProduct.setOnClickListener {
            // Tampilkan BottomSheet

            val filterBottomSheet = FilterFragment()
            filterBottomSheet.setFilterListener(object : FilterFragment.FilterListener {
                override fun onCategorySelected(category: String) {
                    viewModel.getProductsByCategory(category)
                    setupLoading(true)

                }

                override fun onSortingSelected(sort: String) {
                    viewModel.getProductBySorting(sort)
                    setupLoading(true)
                }

                override fun onLimitSelected(limit: Int) {
                    viewModel.getProductsByLimit(limit)
                    setupLoading(true)
                }

                override fun onResetSelected(isReset: Boolean) {
                    if (isReset) {
                        viewModel.getAllProducts()
                        setupLoading(true)
                    }

                }


            })
            filterBottomSheet.show(parentFragmentManager, "FilterBottomSheet")
        }
    }


    private fun setupViewModel() {
        val factory = Injector.provideViewModelFactory()
        viewModel = ViewModelProvider(requireActivity(), factory)[ProductViewModel::class.java]
    }

    private fun setupLoading(loading: Boolean) {
        if (loading) {
            binding.rvProduct.visibility = View.GONE
            binding.rvShimmerProduct.visibility = View.VISIBLE
        } else {
            binding.rvProduct.visibility = View.VISIBLE
            binding.rvShimmerProduct.visibility = View.GONE
        }
    }

    private fun setupShimmerProduct() {
        val productShimmerAdapter = ProductShimmerAdapter()
        binding.rvShimmerProduct.adapter = productShimmerAdapter
        binding.rvShimmerProduct.layoutManager =
            GridLayoutManager(requireActivity(), 2, LinearLayoutManager.VERTICAL, false)
        binding.rvProduct.setHasFixedSize(true)
    }


    private fun setupObserver() {
        viewModel.product.observe(viewLifecycleOwner) {
            val adapter = ProductAdapter(it)
            searchListener(it, adapter)
            adapter.onItemClickCallback(object : ProductAdapter.OnItemClickCallback {
                override fun onClicked(data: Product) {
                    val intent = Intent(requireActivity(), DetailProductActivity::class.java)
                    intent.putExtra(DetailProductActivity.PRODUCT_ID, data.id)
                    startActivity(intent)
                }

            })

            binding.rvProduct.adapter = adapter
            binding.rvProduct.layoutManager =
                GridLayoutManager(requireActivity(), 2, GridLayoutManager.VERTICAL, false)
            binding.rvProduct.setHasFixedSize(true)


            Log.e("productFragment", it.toString())
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            setupLoading(it)
        }

    }


    private fun searchListener(items: List<Product>, adapter: ProductAdapter) {
        binding.searchProduct.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
                    val filteredData = items.filter {
                        it.productName!!.contains(query, true)
                    }

                    if (filteredData.isNotEmpty()) {
                        adapter.updateListData(filteredData)
                        return true
                    } else {
                        adapter.updateListData(filteredData)

                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    val filteredData = items.filter {
                        it.productName!!.contains(newText, true)

                    }

                    return if (filteredData.isNotEmpty()) {
                        adapter.updateListData(filteredData)
                        true
                    } else {
                        adapter.updateListData(filteredData)
                        false
                    }
                }
                return false
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveSelectedCategory(requireActivity(), null)
        saveSelectedLimit(requireActivity(), 0)
        saveSelectedSort(requireActivity(), null)
    }


    private fun getAllProducts() {
        viewModel.getAllProducts()
    }

}
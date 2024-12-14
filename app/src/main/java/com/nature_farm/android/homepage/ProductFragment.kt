package com.nature_farm.android.homepage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nature_farm.android.homepage.adapter.ProductAdapter
import com.nature_farm.android.homepage.core.data.di.Injector
import com.nature_farm.android.homepage.core.data.domain.model.Product
import com.nature_farm.android.homepage.databinding.FragmentProductBinding
import com.nature_farm.android.homepage.ui.detail.DetailProductActivity
import com.nature_farm.android.homepage.ui.main.product.ProductShimmerAdapter
import com.nature_farm.android.homepage.ui.main.product.ProductViewModel
import kotlin.io.path.fileVisitor


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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObserver()
        getAllProducts()

        setupShimmerProduct()
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
            searchListener(it,adapter)
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
                        it.productName!!.contains(query,true)
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
                        it.productName!!.contains(newText,true)

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

    private fun getAllProducts() {
        viewModel.getAllProducts()
    }

}
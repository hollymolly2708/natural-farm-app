package com.nature_farm.android.homepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.nature_farm.android.homepage.adapter.ProductAdapter
import com.nature_farm.android.homepage.core.data.di.Injector
import com.nature_farm.android.homepage.databinding.FragmentProductBinding
import com.nature_farm.android.homepage.ui.main.product.ProductViewModel


class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: ProductViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObserver()
        getAllProducts()
    }

    private fun setupViewModel() {
        val factory = Injector.provideViewModelFactory()
        viewModel = ViewModelProvider(requireActivity(), factory)[ProductViewModel::class.java]
    }

    private fun setupObserver() {
        viewModel.product.observe(viewLifecycleOwner) {
            val adapter = ProductAdapter(it)
            Log.e("productFragment", it.toString())
        }

    }

    private fun getAllProducts() {
        viewModel.getAllProducts()
    }

}
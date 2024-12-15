package com.natural_farm.android.e_commerce.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.natural_farm.android.e_commerce.core.data.di.Injector
import com.natural_farm.android.e_commerce.core.data.domain.model.DetailProduct
import com.natural_farm.android.e_commerce.databinding.FragmentDescriptionBinding
import com.natural_farm.android.e_commerce.ui.main.product.ProductViewModel

class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private lateinit var productViewModel: ProductViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObservers()

    }

    private fun setupViewModel() {
        val factory = Injector.provideViewModelFactory()
        productViewModel =
            ViewModelProvider(requireActivity(), factory)[ProductViewModel::class.java]
    }

    private fun setupObservers() {
        productViewModel.detailProduct.observe(viewLifecycleOwner) { detailProduct ->
            bindingViews(detailProduct)
        }
    }

    private fun bindingViews(detailProduct: DetailProduct) {
        binding.tvInputDescription.text = detailProduct.description
        binding.tvInputName.text = detailProduct.productName
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }


}
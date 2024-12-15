package com.nature_farm.android.homepage.ui.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nature_farm.android.homepage.core.data.di.Injector
import com.nature_farm.android.homepage.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewmodel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObservers()
        getUserProfile()
        binding.layoutProfile.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

    }

    private fun setupViewModel() {
        val factory = Injector.provideViewModelFactory()
        viewmodel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
    }

    private fun setupObservers() {
        viewmodel.user.observe(viewLifecycleOwner) {
            binding.tvAddress.text = it.address
            binding.tvUsername.text = it.username
            binding.tvPhone.text = it.phone
            binding.tvName.text = it.name
            binding.tvEmail.text = it.email
        }

        viewmodel.loading.observe(viewLifecycleOwner) {
            setupLoading(it)
        }
    }

    private fun getUserProfile() {
        viewmodel.getUserProfile(1)
    }

    private fun setupLoading(loading: Boolean) {

        if (loading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.layoutProfile.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.layoutProfile.visibility = View.VISIBLE
        }
    }


}
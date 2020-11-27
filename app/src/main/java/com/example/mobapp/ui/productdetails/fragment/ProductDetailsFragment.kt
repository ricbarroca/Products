package com.example.mobapp.ui.productdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mobapp.core.extensions.loadImageFromUrl
import com.example.mobapp.databinding.ProductDetailsLayoutBinding
import com.example.mobapp.ui.productdetails.viewmodel.ProductDetailsViewModel
import com.example.mobapp.ui.productdetails.viewmodel.binding.ProductDetailsUiModel
import com.example.mobapp.ui.productdetails.viewmodel.binding.ProductDetailsUiState

class ProductDetailsFragment : Fragment() {

    private var _binding: ProductDetailsLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductDetailsLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchProductDetails()
        initObservers()
    }

    private fun initObservers() {
        viewModel.getUiObservable().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ProductDetailsUiState.PopulateUi -> populateUi(it.uiModel)
                is ProductDetailsUiState.Error -> showError()
            }
        })
    }

    private fun populateUi(uiModel: ProductDetailsUiModel) = binding.run {
        image.loadImageFromUrl(uiModel.imageUrl)
        nameTv.text = uiModel.name
        priceTv.text = uiModel.price
    }

    private fun showError() {
        // do something cool
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
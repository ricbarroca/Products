package com.example.mobapp.ui.productslist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.mobapp.core.extensions.hide
import com.example.mobapp.core.extensions.show
import com.example.mobapp.data.models.Product
import com.example.mobapp.databinding.ProductsListLayoutBinding
import com.example.mobapp.ui.productslist.adapter.OnProductClickListener
import com.example.mobapp.ui.productslist.adapter.ProductsListAdapter
import com.example.mobapp.ui.productslist.viewmodel.ProductsListViewModel
import com.example.mobapp.ui.productslist.viewmodel.binding.ProductListUiModel
import com.example.mobapp.ui.productslist.viewmodel.binding.ProductListUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsListFragment : Fragment(), OnProductClickListener {

    private var _binding: ProductsListLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsListViewModel by viewModels()

    private val adapter by lazy { ProductsListAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductsListLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    private fun initObservers() {
        viewModel.getUiObservable().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ProductListUiState.Loading -> showLoading()
                is ProductListUiState.PopulateUi -> populateUi(it.uiModel)
                is ProductListUiState.Error -> showError()
            }
        })
    }

    private fun populateUi(uiModel: ProductListUiModel) {
        binding.progressBar.hide()
        adapter.setList(uiModel.adapterList)
    }

    private fun showError() {
        binding.progressBar.hide()
        binding.errorRoot.run {
            root.show()
            retryBtn.setOnClickListener { viewModel.getProducts() }
        }
        adapter.setList(emptyList())
    }

    private fun showLoading() {
        binding.errorRoot.root.hide()
        binding.progressBar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onProductClicked(product: Product) {
        val action = ProductsListFragmentDirections.fromProductsListToDetailsAction(product)
        NavHostFragment.findNavController(this).navigate(action)
    }
}
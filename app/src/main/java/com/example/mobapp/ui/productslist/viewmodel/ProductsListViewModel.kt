package com.example.mobapp.ui.productslist.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mobapp.core.connection.ResponseResult
import com.example.mobapp.data.repository.ProductsRepository
import com.example.mobapp.ui.productslist.provider.ProductListProvider
import com.example.mobapp.ui.productslist.viewmodel.binding.ProductListUiModel
import com.example.mobapp.ui.productslist.viewmodel.binding.ProductListUiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductsListViewModel @ViewModelInject constructor(
    private val productsRepository: ProductsRepository,
    provider: ProductListProvider
) : ViewModel() {

    private val uiModel = ProductListUiModel(provider)

    private val uiObservable = MutableLiveData<ProductListUiState>()

    fun getUiObservable(): LiveData<ProductListUiState> = uiObservable

    init {
        getProducts()
    }

    fun getProducts() = uiObservable.run {
        value = ProductListUiState.Loading
        viewModelScope.launch {
            productsRepository.getProducts().collect { response ->
                value = when (response) {
                    is ResponseResult.Success -> ProductListUiState.PopulateUi(uiModel.apply { products = response.data })
                    is ResponseResult.Error -> ProductListUiState.Error
                }
            }
        }
    }
}
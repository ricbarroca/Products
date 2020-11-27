package com.example.mobapp.ui.productdetails.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mobapp.data.models.Product
import com.example.mobapp.ui.productdetails.viewmodel.binding.ProductDetailsUiModel
import com.example.mobapp.ui.productdetails.viewmodel.binding.ProductDetailsUiState

class ProductDetailsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
)  : ViewModel() {

    companion object {
        private const val PRODUCT_TAG = "product"
    }

    private val uiModel = ProductDetailsUiModel()

    private val uiObservable = MutableLiveData<ProductDetailsUiState>()

    fun getUiObservable(): LiveData<ProductDetailsUiState> = uiObservable

    fun fetchProductDetails() {
        uiObservable.value = savedStateHandle.get<Product>(PRODUCT_TAG)?.let {
            ProductDetailsUiState.PopulateUi(uiModel.apply { product = it })
        } ?: ProductDetailsUiState.Error
    }
}
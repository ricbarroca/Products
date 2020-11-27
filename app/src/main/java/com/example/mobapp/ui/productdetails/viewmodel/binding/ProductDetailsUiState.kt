package com.example.mobapp.ui.productdetails.viewmodel.binding

sealed class ProductDetailsUiState {

    data class PopulateUi(val uiModel: ProductDetailsUiModel) : ProductDetailsUiState()
    object Error : ProductDetailsUiState()
}
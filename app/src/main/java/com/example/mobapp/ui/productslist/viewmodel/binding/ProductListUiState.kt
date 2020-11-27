package com.example.mobapp.ui.productslist.viewmodel.binding

sealed class ProductListUiState {

    object Loading : ProductListUiState()
    data class PopulateUi(val uiModel: ProductListUiModel) : ProductListUiState()
    object Error : ProductListUiState()
}
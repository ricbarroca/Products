package com.example.mobapp.ui.productslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobapp.core.constants.BASE_URL
import com.example.mobapp.core.extensions.loadImageFromUrl
import com.example.mobapp.data.models.Product
import com.example.mobapp.databinding.ProductHeaderRowBinding
import com.example.mobapp.databinding.ProductItemRowBinding
import com.example.mobapp.ui.productslist.provider.ProductListItem

interface OnProductClickListener {

    fun onProductClicked(product: Product)
}

class ProductsListAdapter(private val clickListener: OnProductClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val HEADER_ITEM = 0
        const val MUTATION_ITEM = 1
    }

    private var listItem = mutableListOf<ProductListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_ITEM -> HeaderViewHolder(ProductHeaderRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            MUTATION_ITEM -> ProductViewHolder(ProductItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalStateException()
        }
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(listItem[position] as ProductListItem.Header)
            is ProductViewHolder -> holder.bind(listItem[position] as ProductListItem.ProductRow)
        }
    }

    override fun getItemViewType(position: Int): Int = when (listItem[position]) {
        is ProductListItem.Header -> HEADER_ITEM
        is ProductListItem.ProductRow -> MUTATION_ITEM
    }

    fun setList(newList: List<ProductListItem>) {
        listItem.clear()
        listItem.addAll(newList)
        notifyDataSetChanged()
    }

    inner class HeaderViewHolder(private val binding: ProductHeaderRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(header: ProductListItem.Header) {
            binding.title.text = header.title
        }
    }

    inner class ProductViewHolder(private val binding: ProductItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(productItem: ProductListItem.ProductRow) {
            binding.productImage.loadImageFromUrl("$BASE_URL${productItem.product.url}")
            binding.productName.text = productItem.product.name
            binding.root.setOnClickListener { clickListener.onProductClicked(productItem.product) }
        }
    }
}
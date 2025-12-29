package com.example.shop.UI.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shop.domain.model.Product
import com.example.shop.databinding.ItemProductBinding

class ProductAdapter(private val click: (Product) -> Unit) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDifutil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.onBind(product)
        holder.itemView.setOnClickListener {
            click(product)
        }
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(product: Product) {
            binding.textTitle.text = product.title
            binding.textPrice.text = "${product.price} $"

            binding.imageProduct.load(product.image) {
                crossfade(true)
                crossfade(500)
            }
        }
    }

    class ProductDifutil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }
    }
}

package com.sergeenko.alexey.redsofttesttask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sergeenko.alexey.redsofttesttask.dataClasses.Product
import com.sergeenko.alexey.redsofttesttask.databinding.ProductBinding
import com.sergeenko.alexey.redsofttesttask.basket.Basket
import com.sergeenko.alexey.redsofttesttask.handlers.ProductPressHandler

class ProductAdapter(private var basket: Basket) : PagedListAdapter<Product, RecyclerView.ViewHolder>(
    UserDiffCallback
) {
    private val myHandler =
        ProductPressHandler(basket)

    class ProductViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var binding: ProductBinding? =
            DataBindingUtil.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ProductBinding =
            ProductBinding.inflate(
                inflater,
                parent,
                false
            )
        return ProductViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { Basket.checkBasket(it) }
        (holder as ProductViewHolder).binding?.apply {
            handler = myHandler
            product = getItem(position)
        }
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }
}
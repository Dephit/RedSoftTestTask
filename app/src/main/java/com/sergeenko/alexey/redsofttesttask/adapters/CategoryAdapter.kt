package com.sergeenko.alexey.redsofttesttask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sergeenko.alexey.redsofttesttask.dataClasses.Category
import com.sergeenko.alexey.redsofttesttask.databinding.CategoryBinding

class CategoryAdapter(private val data: List<Category>?) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
        class CategoryViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var binding: CategoryBinding? =
            DataBindingUtil.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CategoryBinding =
            CategoryBinding.inflate(
                inflater,
                parent,
                false
            )
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        (holder as CategoryViewHolder).binding?.apply {
            category = data?.get(position)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}
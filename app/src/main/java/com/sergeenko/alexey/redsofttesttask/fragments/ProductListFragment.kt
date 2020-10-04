package com.sergeenko.alexey.redsofttesttask.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sergeenko.alexey.redsofttesttask.R
import com.sergeenko.alexey.redsofttesttask.api.Product
import com.sergeenko.alexey.redsofttesttask.databinding.ProductBinding
import kotlinx.android.synthetic.main.product_list_fragment.*

class ProductListFragment : BaseFragment() {

    companion object {
        fun newInstance() = ProductListFragment()
    }

    private lateinit var viewModel: ProductListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.product_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setViewModel()
        setUserAdapter()
        setEventListeners()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)
    }

    private fun setEventListeners() {
        refresh_button.setOnClickListener {
            viewModel.refresh()
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchProducts(newText)
                return true
            }
        })
    }

    private fun setUserAdapter() {
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val userAdapter = ProductAdapter(viewModel.basket)
        list.layoutManager = linearLayoutManager
        list.adapter = userAdapter
        viewModel.userList.observe(viewLifecycleOwner, { userAdapter.submitList(it) })
        viewModel.getErrorState().observe(viewLifecycleOwner, {
            refresh_button.visibility = if (it) { View.VISIBLE } else { View.GONE }
        })
    }
}


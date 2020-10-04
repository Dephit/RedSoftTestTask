package com.sergeenko.alexey.redsofttesttask.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergeenko.alexey.redsofttesttask.R
import com.sergeenko.alexey.redsofttesttask.viewModels.ProductListViewModel
import com.sergeenko.alexey.redsofttesttask.adapters.ProductAdapter
import kotlinx.android.synthetic.main.product_list_fragment.*
import kotlinx.android.synthetic.main.search.view.*


class MainActivity : BaseActivity() {

    private lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_list_fragment)
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

        search_view.apply {
            edt_search_text.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.searchProducts(v.text.trim().toString())
                    return@OnEditorActionListener true
                }
                false
            })

            val searchViewTextWatcher =  object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.searchProducts(s?.trim().toString())
                }

                override fun afterTextChanged(s: Editable?) {}
            }

            iv_clear_text.setOnClickListener {
                if(edt_search_text.text.isNotEmpty()) {
                    edt_search_text.setText("")
                    viewModel.searchProducts()
                }
            }

            edt_search_text.addTextChangedListener(searchViewTextWatcher)
        }
    }

    override fun onResume() {
        super.onResume()
        list.adapter?.notifyDataSetChanged()
    }

    private fun setUserAdapter() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val userAdapter = ProductAdapter(viewModel.basket)
        list.layoutManager = linearLayoutManager
        list.adapter = userAdapter
        viewModel.userList.observe(this, { userAdapter.submitList(it) })
        viewModel.getErrorState().observe(this, {
            refresh_button.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })
    }
}

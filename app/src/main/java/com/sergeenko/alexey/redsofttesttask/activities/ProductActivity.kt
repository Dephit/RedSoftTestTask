package com.sergeenko.alexey.redsofttesttask.activities

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergeenko.alexey.redsofttesttask.R
import com.sergeenko.alexey.redsofttesttask.adapters.CategoryAdapter
import com.sergeenko.alexey.redsofttesttask.viewModels.ProductViewModel
import com.sergeenko.alexey.redsofttesttask.dataClasses.Category
import com.sergeenko.alexey.redsofttesttask.utils.PRODUCT_ID_EXTRA
import com.sergeenko.alexey.redsofttesttask.databinding.ActivityProductBinding
import com.sergeenko.alexey.redsofttesttask.handlers.ProductPressHandler
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : BaseActivity() {

    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
        setToolbar()
    }

    private fun setToolbar() {
        toolbar.apply {
            setSupportActionBar(this)
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setViewModel() {
        val binding: ActivityProductBinding = DataBindingUtil.setContentView(this, R.layout.activity_product)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        viewModel.getProduct(intent.getIntExtra(PRODUCT_ID_EXTRA, -1))
        //to load catigories from API
        //viewModel.getCategory()
        viewModel.productLiveData.observe(this, {
            it?.let {
                binding.apply {
                    viewModel.checkBasket(it)
                    product = it
                    handler = ProductPressHandler(viewModel.basket)
                    //to load catigories from product
                    setCategoryList(it.categories)
                }
            } ?: Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
        })
        viewModel.categoryLiveData.observe(this, {
            it?.let {
                setCategoryList(it.data)
            }
        })
    }

    private fun setCategoryList(data: List<Category>?) {
        category_list.layoutManager = LinearLayoutManager(this)
        category_list.adapter = CategoryAdapter(data)
    }
}


package com.sergeenko.alexey.redsofttesttask.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.sergeenko.alexey.redsofttesttask.R

class ProductCardFragment : Fragment() {

    companion object {
        fun newInstance(id: Int) = ProductCardFragment()
    }

    private lateinit var viewModel: ProductCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_card_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductCardViewModel::class.java)

    }

}
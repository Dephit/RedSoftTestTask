package com.sergeenko.alexey.redsofttesttask.fragments

import android.content.Context
import com.sergeenko.alexey.redsofttesttask.activities.MainActivity
import com.sergeenko.alexey.redsofttesttask.api.Product

class ProductPressHandler(private var basket: Basket) {

    fun addToBasket(product: Product?) {
        product?.let {
            basket.addToBasket(it)
        }
    }

    fun openProduct(context: Context, product: Product?) {
        product?.id?.let { (context as MainActivity).setProductCardFragment(it) }
    }

    fun removeFromBasket(product: Product?) {
        product?.let {
            basket.removeFromBasket(it)
        }
    }
}
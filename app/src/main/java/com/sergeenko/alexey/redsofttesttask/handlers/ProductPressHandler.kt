package com.sergeenko.alexey.redsofttesttask.handlers

import android.content.Context
import android.content.Intent
import com.sergeenko.alexey.redsofttesttask.activities.MainActivity
import com.sergeenko.alexey.redsofttesttask.activities.ProductActivity
import com.sergeenko.alexey.redsofttesttask.utils.PRODUCT_ID_EXTRA
import com.sergeenko.alexey.redsofttesttask.dataClasses.Product
import com.sergeenko.alexey.redsofttesttask.basket.Basket

class ProductPressHandler(private var basket: Basket) {

    fun addToBasket(product: Product?) {
        product?.let {
            Basket.addToBasket(it)
        }
    }

    fun openProduct(context: Context, product: Product?) {
        product?.id?.let {
            (context as MainActivity).startActivity(
                Intent(context, ProductActivity::class.java)
                    .putExtra(PRODUCT_ID_EXTRA, it)
            )
        }
    }

    fun removeFromBasket(product: Product?) {
        product?.let {
            Basket.removeFromBasket(it)
        }
    }
}
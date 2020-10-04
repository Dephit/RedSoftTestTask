package com.sergeenko.alexey.redsofttesttask.basket

import com.sergeenko.alexey.redsofttesttask.dataClasses.Product
import com.sergeenko.alexey.redsofttesttask.dataClasses.BasketPosition


object Basket{

    private var basketList: MutableList<BasketPosition> = mutableListOf()

    fun addToBasket(product: Product, amount: Int = 1){
        if (product.amount > 0) {
            basketList.find { it.productId == product.id }?.let {
                it.amount += amount
                if (product.amount < it.amount) {
                    it.amount = product.amount
                }
            } ?: basketList.add(BasketPosition(product.id, amount))
            checkBasket(product)
        }
    }

    fun removeFromBasket(product: Product, amount: Int = 1){
        basketList.find { it.productId == product.id }?.let {
            it.amount -= amount
            if(it.amount <= 0){
                basketList.remove(it)
            }
        }
        checkBasket(product)
    }

    fun checkBasket(product: Product){
        product.setBasket(isProductInBasket(product), getProductAmount(product))
    }

    private fun isProductInBasket(product: Product) = basketList.find { it.productId == product.id }?.let { true } ?: false

    private fun getProductAmount(product: Product) = basketList.find { it.productId == product.id }?.amount ?: 0
}

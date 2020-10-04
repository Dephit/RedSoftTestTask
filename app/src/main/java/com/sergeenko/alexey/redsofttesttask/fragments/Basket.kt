package com.sergeenko.alexey.redsofttesttask.fragments

import com.sergeenko.alexey.redsofttesttask.api.Product

data class Basket(var basketList: MutableList<BasketPosition>){

    fun addToBasket(product: Product, amount: Int = 1){
        if (product.amount > 0) {
            basketList.find { it.product.id == product.id }?.let {
                it.amount += amount
                if (product.amount < it.amount) {
                    it.amount = product.amount
                }
            } ?: basketList.add(BasketPosition(product, amount))
            checkBasket(product)
        }
    }

    fun removeFromBasket(product: Product, amount: Int = 1){
        basketList.find { it.product.id == product.id }?.let {
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

    private fun isProductInBasket(product: Product) = basketList.find { it.product.id == product.id }?.let { true } ?: false

    private fun getProductAmount(product: Product) = basketList.find { it.product.id == product.id }?.amount ?: 0
}
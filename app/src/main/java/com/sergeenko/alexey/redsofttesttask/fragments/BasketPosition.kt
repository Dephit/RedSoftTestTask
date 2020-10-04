package com.sergeenko.alexey.redsofttesttask.fragments

import com.sergeenko.alexey.redsofttesttask.api.Product

data class BasketPosition(var product: Product, var amount: Int = 0){
    init {
        if(product.amount < amount){
            amount = product.amount
        }
    }
}
package com.sergeenko.alexey.redsofttesttask.api

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import com.sergeenko.alexey.redsofttesttask.BR


data class Products(var data: List<Product>? = null)

data class Category(
    var id: Int = 0,
    var title: String? = "",
    var parent_id: Int = 0
)

data class Product(
    var id: Int = 0,
    var title: String? = "",
    var short_description: String? = "",
    var image_url: String? = "",
    var amount: Int = 0,
    var price: Double = 0.0,
    var producer: String? = "",
    var categories: List<Category>? = null
): Observable{
    private val registry = PropertyChangeRegistry()
    @Bindable
    var inBasket = false
    @Bindable
    var basketAmount = 0

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback)
    }

    fun setBasket(b: Boolean, amount: Int) {
        registry.apply {
            inBasket = b
            basketAmount = amount
            notifyChange(this@Product, BR.inBasket)
            notifyChange(this@Product, BR.basketAmount)
        }

    }

}
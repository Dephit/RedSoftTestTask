package com.sergeenko.alexey.redsofttesttask.viewModels

import androidx.lifecycle.ViewModel
import com.sergeenko.alexey.redsofttesttask.api.RedSoftApi
import com.sergeenko.alexey.redsofttesttask.utils.NetworkService
import com.sergeenko.alexey.redsofttesttask.basket.Basket

open class BaseModel : ViewModel() {
    val basket = Basket
    val api: RedSoftApi = NetworkService.retrofitService()

}

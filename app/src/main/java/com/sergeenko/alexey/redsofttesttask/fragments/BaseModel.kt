package com.sergeenko.alexey.redsofttesttask.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.sergeenko.alexey.redsofttesttask.activities.BaseActivity
import com.sergeenko.alexey.redsofttesttask.api.RedSoftApi
import com.sergeenko.alexey.redsofttesttask.dagger.NetworkService
import retrofit2.Retrofit

open class BaseModel : ViewModel() {

    var api: RedSoftApi = NetworkService.retrofitService()

}

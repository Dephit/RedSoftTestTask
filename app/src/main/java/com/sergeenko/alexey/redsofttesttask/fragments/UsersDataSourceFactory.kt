package com.sergeenko.alexey.redsofttesttask.fragments

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sergeenko.alexey.redsofttesttask.api.Product
import com.sergeenko.alexey.redsofttesttask.api.RedSoftApi

class UsersDataSourceFactory(private val api: RedSoftApi) : DataSource.Factory<Long, Product>() {
    val usersDataSourceLiveData = MutableLiveData<UsersDataSource>()
    private var text: String = ""

    override fun create(): DataSource<Long, Product> {
        val usersDataSource = UsersDataSource(api, text)
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }

    fun search(text: String = ""): DataSource<Long, Product>{
        this.text = text
        return create()
    }
}
package com.sergeenko.alexey.redsofttesttask.utils

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.sergeenko.alexey.redsofttesttask.api.*
import com.sergeenko.alexey.redsofttesttask.dataClasses.Product
import com.sergeenko.alexey.redsofttesttask.dataClasses.Products
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersDataSource(private val api: RedSoftApi, private var searchText: String = "") : ItemKeyedDataSource<Long, Product>() {

    private var key = 0L
    val errorState = MutableLiveData(false)
    lateinit var refreshCall: ()-> Unit

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Product>) {
        getProducts(callback){ loadInitial(params, callback) }
    }

    private fun getProducts(callback: LoadCallback<Product>, onErrorCall: ()->Unit)  =
        CoroutineScope(Dispatchers.IO).launch{ api.getProducts(filterTitle = searchText, startFrom = key, sort = sortTitle)
            .enqueue(object : Callback<Products> {
                override fun onResponse(call: Call<Products>, response: Response<Products>) {
                    if(response.isSuccessful) {
                        key += pageSize
                        response.body()?.data?.let {
                            callback.onResult(it)
                        }
                    }
                }

                override fun onFailure(call: Call<Products>, t: Throwable) {
                    setRefresh {  onErrorCall() }
                }
            })}

    private fun setRefresh(func: ()-> Unit) {
        refreshCall = func
        errorState.postValue(true)
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Product>) {
        getProducts(callback){
            loadAfter(params, callback)
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Product>) {}

    override fun getKey(item: Product): Long {
        return item.id.toLong()
    }

    fun refresh() {
        refreshCall()
        errorState.postValue(false)
    }
}
package com.sergeenko.alexey.redsofttesttask.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sergeenko.alexey.redsofttesttask.dataClasses.Product
import com.sergeenko.alexey.redsofttesttask.utils.pageSize
import com.sergeenko.alexey.redsofttesttask.utils.UsersDataSourceFactory

class ProductListViewModel() : BaseModel() {

    var userList: LiveData<PagedList<Product>>
    private val sourceFactory: UsersDataSourceFactory = UsersDataSourceFactory(api)

    fun getErrorState() = Transformations.switchMap(sourceFactory.usersDataSourceLiveData) { it.errorState }

    fun refresh() {
        sourceFactory.usersDataSourceLiveData.value?.refresh()
    }

    fun searchProducts(newText: String? = "") {
        newText?.let {
            sourceFactory.search(it)
            sourceFactory.usersDataSourceLiveData.value!!.invalidate()
        }
    }

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(true)
            .build()
        userList = LivePagedListBuilder(sourceFactory, config).build()
    }

}


package com.sergeenko.alexey.redsofttesttask.viewModels

import androidx.lifecycle.MutableLiveData
import com.sergeenko.alexey.redsofttesttask.dataClasses.CategoryResponse
import com.sergeenko.alexey.redsofttesttask.dataClasses.Product
import com.sergeenko.alexey.redsofttesttask.dataClasses.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : BaseModel(){

    fun getProduct(intExtra: Int) {
        api.getProduct(intExtra).enqueue(
            object : Callback<ProductResponse>{
                override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                    if(response.isSuccessful){
                        productLiveData.postValue(response.body()?.data)
                    }
                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    productLiveData.postValue(null)
                }

            }
        )
    }

    fun getCategory() {
        api.getCategory().enqueue(
            object : Callback<CategoryResponse>{
                override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                    if(response.isSuccessful){
                        categoryLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                    categoryLiveData.postValue(null)
                }

            }
        )
    }

    fun checkBasket(it: Product) {
        basket.checkBasket(it)
    }


    val categoryLiveData = MutableLiveData<CategoryResponse?>()
    val productLiveData = MutableLiveData<Product?>()


}
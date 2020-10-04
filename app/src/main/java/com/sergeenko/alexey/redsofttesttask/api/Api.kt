package com.sergeenko.alexey.redsofttesttask.api

import com.sergeenko.alexey.redsofttesttask.dataClasses.CategoryResponse
import com.sergeenko.alexey.redsofttesttask.dataClasses.ProductResponse
import com.sergeenko.alexey.redsofttesttask.dataClasses.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RedSoftApi {

    @GET("api/v1/products")
    fun getProducts(
        @Query("filter[title]") filterTitle: String = "",
        @Query("filter[categoryId]") filterCategoryId: Int? = null,
        @Query("filter[parentCategoryId]") filterParentCategoryI: Int? = null,
        @Query("startFrom") startFrom: Long = 0,
        @Query("maxItems") maxItems: Int = 15,
        @Query("sort") sort: String? = null
        ): Call<Products>

    @GET("/api/v1/products/{productId}")
    fun getProduct(
        @Path("productId") productId: Int? = null,
    ): Call<ProductResponse>

    @GET("/api/v1/categories")
    fun getCategory(): Call<CategoryResponse>
}

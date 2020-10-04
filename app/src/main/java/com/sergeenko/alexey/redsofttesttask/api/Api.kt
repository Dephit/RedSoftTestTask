package com.sergeenko.alexey.redsofttesttask.api

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sergeenko.alexey.redsofttesttask.R
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
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
}

const val sortMinusPrice = "-price"
const val sortPrice = "price"
const val sortTitle = "title"
const val sortMinusTitle = "-title"
const val pageSize = 20

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, v: String?):Unit {
    Picasso
        .with(imageView.context)
        .load(v)
        .fit()
        .placeholder(R.drawable.no_imge)
        .into(imageView)
}
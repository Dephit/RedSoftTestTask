package com.sergeenko.alexey.redsofttesttask.dagger

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sergeenko.alexey.redsofttesttask.activities.BaseActivity
import com.sergeenko.alexey.redsofttesttask.api.RedSoftApi
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

class ServiceInterceptor : Interceptor{

    private var token : String = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if(request.header("No-Authentication") == null){
            if(token.isNotEmpty()) {
                val finalToken = "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build()
            }
        }

        return chain.proceed(request)
    }

}

object NetworkService {

    private const val BASE_URL = "https://rstestapi.redsoftdigital.com/"

    // HttpLoggingInterceptor выводит подробности сетевого запроса в логи
    private val loggingInterceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val baseInterceptor: Interceptor = ServiceInterceptor()

    private val client: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(baseInterceptor)
        .build()

    fun retrofitService(): RedSoftApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RedSoftApi::class.java)
    }
}

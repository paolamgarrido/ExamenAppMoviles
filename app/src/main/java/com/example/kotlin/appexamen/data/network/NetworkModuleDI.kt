package com.example.kotlin.appexamen.data.network

import com.example.kotlin.appexamen.utilities.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDI {

    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()

    private val okHttpClient: OkHttpClient = OkHttpClient()

    operator fun invoke(): DragonballAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(DragonballAPIService::class.java)
    }
}
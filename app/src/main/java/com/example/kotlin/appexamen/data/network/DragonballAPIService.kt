package com.example.kotlin.appexamen.data.network

import com.example.kotlin.appexamen.data.network.model.DragonballObject
import retrofit2.http.GET
import retrofit2.http.Query

interface DragonballAPIService {

    @GET("characters")
    suspend fun getDragonballList(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10 // Default
    ): DragonballObject
}
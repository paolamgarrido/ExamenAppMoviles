package com.example.kotlin.appexamen.data.network

import com.example.kotlin.appexamen.data.network.model.DragonballObject

class DragonballAPIClient {
    private lateinit var api: DragonballAPIService

    suspend fun getDragonballList(page: Int): DragonballObject? {
        api = NetworkModuleDI()

        return try {
            api.getDragonballList(page = page)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }
}
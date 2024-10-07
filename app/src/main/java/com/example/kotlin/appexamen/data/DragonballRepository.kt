package com.example.kotlin.appexamen.data

import android.util.Log
import com.example.kotlin.appexamen.data.network.DragonballAPIClient
import com.example.kotlin.appexamen.data.network.model.DragonballObject

class DragonballRepository {
    private val apiDragonball = DragonballAPIClient()

    suspend fun getDragonballList(page: Int): DragonballObject? =
        try {
            val response = apiDragonball.getDragonballList(page = page)
            Log.d("DragonballRepository", "Consulta exitosa: $response")
            response
        } catch (e: Exception) {
            Log.e("Falla en getDragonballList", "Error en la consulta de getDragonballList: ${e.message}")
            null
        }
}
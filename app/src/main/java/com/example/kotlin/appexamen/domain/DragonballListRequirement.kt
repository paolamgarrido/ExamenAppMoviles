package com.example.kotlin.appexamen.domain

import com.example.kotlin.appexamen.data.DragonballRepository
import com.example.kotlin.appexamen.data.network.model.DragonballObject

class DragonballListRequirement {
    private val repository = DragonballRepository()

    suspend operator fun invoke(page: Int): DragonballObject? = repository.getDragonballList(page = page)
}
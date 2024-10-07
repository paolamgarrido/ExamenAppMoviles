package com.example.kotlin.appexamen.frameworks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.appexamen.data.network.model.DragonballObject
import com.example.kotlin.appexamen.data.network.model.DragonballBase
import com.example.kotlin.appexamen.domain.DragonballListRequirement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DragonballViewModel: ViewModel() {

    val dragonballObjectLiveData = MutableLiveData<List<DragonballBase>?>()
    private val dragonballListRequirement = DragonballListRequirement()
    private var currentPage = 1
    private var totalPages: Int = 1
    private val allResults = mutableListOf<DragonballBase>() // Lista para acumular todos los resultados

    fun getDragonballList() {
        viewModelScope.launch(Dispatchers.IO) {
            while (currentPage <= totalPages) {
                val result: DragonballObject? = dragonballListRequirement(page = currentPage)

                result?.let {
                    totalPages = it.meta.totalPages // Actualizamos el número total de páginas
                    currentPage++ // Incrementamos la página para la próxima llamada
                    allResults.addAll(it.results) // Añadimos los resultados a la lista acumulada

                    // Publicamos el resultado acumulado en el LiveData
                    launch(Dispatchers.Main) {
                        dragonballObjectLiveData.postValue(allResults)
                    }
                }
            }
        }
    }
}

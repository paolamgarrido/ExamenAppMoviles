package com.example.kotlin.appexamen.frameworks.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.appexamen.data.network.model.DragonballBase
import com.example.kotlin.appexamen.databinding.ActivityDragonballBinding
import com.example.kotlin.appexamen.frameworks.adapters.DragonballAdapter
import com.example.kotlin.appexamen.frameworks.viewmodel.DragonballViewModel

class DragonballActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDragonballBinding

    private val adapter : DragonballAdapter = DragonballAdapter()

    private val viewModel: DragonballViewModel by viewModels()

    private var dragonballList: ArrayList<DragonballBase> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()

        initializeObservers()

        viewModel.getDragonballList()

        binding.etFilter.addTextChangedListener { query ->
            val filteredList = filterCharacters(query.toString())
            adapter.updateList(filteredList)
        }
    }

    private fun initializeBinding() {
        binding = ActivityDragonballBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    private fun setUpRecyclerView(dataForList: ArrayList<DragonballBase>) {
        dragonballList = dataForList

        binding.RVDragonball.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.RVDragonball.layoutManager = linearLayoutManager

        adapter.DragonballAdapter(dataForList, this)

        binding.RVDragonball.adapter = adapter
    }

    private fun initializeObservers() {
        viewModel.dragonballObjectLiveData.observe(this) { dragonballList ->
            if (dragonballList != null) {
                setUpRecyclerView(ArrayList(dragonballList))
            }
        }
    }

    private fun filterCharacters(query: String): ArrayList<DragonballBase> {
        return if (query.isEmpty()) {
            dragonballList
        } else {
            val filteredList = ArrayList<DragonballBase>()
            for (character in dragonballList) {
                if (character.name.contains(query, ignoreCase = true)) {
                    filteredList.add(character)
                }
            }
            filteredList
        }
    }
}
package com.example.kotlin.appexamen.frameworks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.appexamen.data.network.model.DragonballBase
import com.example.kotlin.appexamen.databinding.ItemDragonballBinding
import com.example.kotlin.appexamen.frameworks.adapters.viewholders.DragonballViewHolder

class DragonballAdapter: RecyclerView.Adapter<DragonballViewHolder>() {

    var data: ArrayList<DragonballBase> = ArrayList()

    lateinit var context: Context

    fun DragonballAdapter(basicData: ArrayList<DragonballBase>, context: Context) {
        this.data = basicData
        this.context = context
    }

    override fun onBindViewHolder(holder: DragonballViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragonballViewHolder {
        val binding = ItemDragonballBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DragonballViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    // Function to update the list dynamically
    fun updateList(newData: ArrayList<DragonballBase>) {
        data = newData
        notifyDataSetChanged()
    }

}
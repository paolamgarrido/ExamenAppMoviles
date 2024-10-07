package com.example.kotlin.appexamen.frameworks.adapters.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.appexamen.data.network.model.DragonballBase
import com.example.kotlin.appexamen.databinding.ItemDragonballBinding

class DragonballViewHolder(
    private val binding: ItemDragonballBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DragonballBase, context: Context) {
        binding.TVCharacterName.text = item.name
        binding.TVCharacterKi.text = item.ki.toString()
        binding.TVCharacterMaxKi.text = item.maxKi.toString()
        binding.TVCharacterRace.text = item.race
        binding.TVCharacterGender.text = item.gender
        binding.TVCharacterAffiliation.text = item.affiliation
        binding.TVDeletedAt.text = item.deletedAt
        binding.TVCharacterDescription.text = item.description

        if (item.url != null) {
            binding.IVCharacterImage.visibility = View.VISIBLE
            val imageUrl = item.url
            getDragonballImage(imageUrl, binding.IVCharacterImage, context)
        } else {
            binding.IVCharacterImage.visibility = View.GONE
        }
    }

    private fun getDragonballImage(url: String, imageView: ImageView, context: Context) {
        val requestOptions = RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .priority(Priority.HIGH)

        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }
}
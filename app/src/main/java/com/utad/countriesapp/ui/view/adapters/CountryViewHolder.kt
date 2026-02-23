package com.utad.countriesapp.ui.view.adapters

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utad.countriesapp.data.model.Pais
import com.utad.countriesapp.databinding.ItemCountryBinding

class CountryViewHolder(
    private val binding: ItemCountryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Pais, onClickListener: (Pais) -> Unit) {
        binding.tvNombre.text = item.traducciones?.espanol?.comun
        Picasso.get().load(item.bandera.imagen).into(binding.ivPais)
        binding.itemCountry.setOnClickListener {
            onClickListener(item)
        }
    }
}
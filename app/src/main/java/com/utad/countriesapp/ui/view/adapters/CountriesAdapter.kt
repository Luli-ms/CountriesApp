package com.utad.countriesapp.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.utad.countriesapp.data.model.Pais
import com.utad.countriesapp.databinding.ItemCountryBinding

class CountriesAdapter(
    private val onClickListener: (Pais) -> Unit
) : ListAdapter<Pais, CountryViewHolder>(CountryDiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CountryViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: CountryViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position), onClickListener)
    }
}
object CountryDiffCallback : DiffUtil.ItemCallback<Pais>() {

    override fun areItemsTheSame(oldItem: Pais, newItem: Pais): Boolean {
        return oldItem.capital == newItem.capital
    }

    override fun areContentsTheSame(oldItem: Pais, newItem: Pais): Boolean {
        return oldItem == newItem
    }
}
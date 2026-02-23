package com.utad.countriesapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.utad.countriesapp.R
import com.utad.countriesapp.data.CountryRepository
import com.utad.countriesapp.data.model.Pais
import com.utad.countriesapp.data.remote.RetrofitClient
import com.utad.countriesapp.databinding.ActivityDetailBinding
import com.utad.countriesapp.ui.viewModel.CountriesViewModel
import com.utad.countriesapp.ui.viewModel.CountriesViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: CountriesViewModel by viewModels {
        CountriesViewModelFactory(CountryRepository(RetrofitClient.apiService))
    }
    private var selectedCountry: Pais? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        selectedCountry = intent.getParcelableExtra("pais", Pais::class.java)
        if (selectedCountry != null) {
            setupUi()
        }
    }

    private fun setupUi() {
        binding.tvNombre.text = selectedCountry?.traducciones?.espanol?.comun
        binding.tvCapital.text = selectedCountry?.capital?.get(0)
        binding.tvPoblacion.text = selectedCountry?.poblacion.toString()
        Picasso.get().load(selectedCountry?.bandera?.imagen).into(binding.ivPais)
    }
}
package com.utad.countriesapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.utad.countriesapp.R
import com.utad.countriesapp.data.CountryRepository
import com.utad.countriesapp.data.remote.RetrofitClient
import com.utad.countriesapp.databinding.ActivityMainBinding
import com.utad.countriesapp.ui.view.adapters.CountriesAdapter
import com.utad.countriesapp.ui.viewModel.CountriesViewModel
import com.utad.countriesapp.ui.viewModel.CountriesViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CountriesViewModel by viewModels {
        CountriesViewModelFactory(CountryRepository(RetrofitClient.apiService))
    }
    private val adapter = CountriesAdapter {}
    private var selectedContinent = "europe"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupUi()
        setupObservers()
    }

    private fun setupUi() {
        viewModel.fetchCountriesByContinent(selectedContinent)
        binding.rvCountries.adapter = adapter
        binding.rvCountries.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        viewModel.countries.observe(this) { countries ->
            adapter.submitList(countries)
        }
    }
}
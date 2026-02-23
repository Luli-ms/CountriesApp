package com.utad.countriesapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.utad.countriesapp.R
import com.utad.countriesapp.data.CountryRepository
import com.utad.countriesapp.data.model.Pais
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
    private val adapter = CountriesAdapter { navigateToDetail(it) }
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
            this.title = when (selectedContinent) {
                "europe" -> getString(R.string.europe)
                "america" -> getString(R.string.america)
                "asia" -> getString(R.string.asia)
                "africa" -> getString(R.string.africa)
                "oceania" -> getString(R.string.oceania)
                else -> getString(R.string.app_name)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.europe -> {
                selectedContinent = "europe"
            }
            R.id.america -> {
                selectedContinent = "america"
            }
            R.id.asia -> {
                selectedContinent = "asia"
            }
            R.id.africa -> {
                selectedContinent = "africa"
            }
            R.id.oceania -> {
                selectedContinent = "oceania"
            }
            else -> return false
        }
        viewModel.fetchCountriesByContinent(selectedContinent)
        return true
    }

    private fun navigateToDetail(pais: Pais) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("pais", pais)
        startActivity(intent)
    }
}
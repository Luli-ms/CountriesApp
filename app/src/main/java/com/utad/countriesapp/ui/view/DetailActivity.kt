package com.utad.countriesapp.ui.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.utad.countriesapp.R
import com.utad.countriesapp.data.model.Pais
import com.utad.countriesapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
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
        this.title = selectedCountry?.traducciones?.espanol?.comun
        binding.tvNombre.text = selectedCountry?.traducciones?.espanol?.comun
        binding.tvCapital.text = getString(R.string.capital)
            .plus(selectedCountry?.capital?.get(0))
        binding.tvPoblacion.text = getString(R.string.poblacion)
            .plus(selectedCountry?.poblacion.toString())
            .plus(getString(R.string.habitantes))
        Picasso.get().load(selectedCountry?.bandera?.imagen).into(binding.ivPais)
    }
}
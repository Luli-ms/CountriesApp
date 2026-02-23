package com.utad.countriesapp.data

import com.utad.countriesapp.data.model.Pais
import com.utad.countriesapp.data.remote.CountriesApiService

class CountryRepository(
    private val apiService: CountriesApiService
) {
    suspend fun getPeliculas(continente: String): List<Pais > {
        return apiService.getPeliculas(continente)
    }
}
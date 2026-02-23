package com.utad.countriesapp.data.remote

import com.utad.countriesapp.data.model.Pais
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApiService {
    // EUROPE
    @GET("region/{continent}?fields=translations,capital,latlng,borders,flags,population")
    suspend fun getPeliculas(
        @Path("continent") continente: String
    ): List<Pais>
}
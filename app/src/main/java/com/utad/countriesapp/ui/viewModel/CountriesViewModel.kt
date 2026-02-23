package com.utad.countriesapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.countriesapp.data.CountryRepository
import com.utad.countriesapp.data.model.Pais
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val repository: CountryRepository
): ViewModel() {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _countries = MutableLiveData<List<Pais>>()
    val countries: LiveData<List<Pais>> get() = _countries

    fun fetchCountriesByContinent(continent: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val countries = repository.getPeliculas(continent)
                _countries.postValue(countries)
            } catch (e: Exception) {
                _error.postValue("Error fetching countries: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
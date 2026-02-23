package com.utad.countriesapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pais(
    @SerializedName("translations") val traducciones: Translations?,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("latlng") val latlng: List<Double>?,
    @SerializedName("borders") val fronteras: List<String>?,
    @SerializedName("flags") val bandera: Flag,
    @SerializedName("population") val poblacion: Int
): Parcelable
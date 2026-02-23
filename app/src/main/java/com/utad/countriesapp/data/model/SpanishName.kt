package com.utad.countriesapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpanishName(@SerializedName("common") val comun: String?): Parcelable
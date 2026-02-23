package com.utad.countriesapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flag(@SerializedName("png") val imagen: String?): Parcelable
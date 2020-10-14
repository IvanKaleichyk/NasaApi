package com.koleychik.nasaapi.models.mainModels

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.koleychik.nasaapi.utils.getImageUri
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarsImageModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("sol")
    val sol: Int,
    @SerializedName("img_src")
    var img_src: String,
    @SerializedName("earth_date")
    val earth_date: String
) : Parcelable

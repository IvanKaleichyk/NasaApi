package com.koleychik.nasaapi.models

import com.google.gson.annotations.SerializedName
import com.koleychik.nasaapi.models.mainModels.MarsImageModel

data class MarsModelList(
    @SerializedName("photos")
    val photos: List<MarsImageModel>
)
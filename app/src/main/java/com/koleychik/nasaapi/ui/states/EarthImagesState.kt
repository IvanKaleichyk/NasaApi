package com.koleychik.nasaapi.ui.states

import com.koleychik.nasaapi.models.mainModels.EarthImageModel

sealed class EarthImagesState{

    object Loading : EarthImagesState()
    class Error(val resText : Int) : EarthImagesState()
    class Show(val list : List<EarthImageModel>) : EarthImagesState()

}
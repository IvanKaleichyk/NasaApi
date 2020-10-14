package com.koleychik.nasaapi.ui.states

import com.koleychik.nasaapi.models.mainModels.MarsImageModel

sealed class MarsImagesState {
    object Loading : MarsImagesState()
    class Error(val resText : Int) : MarsImagesState()
    class Show(val list : List<MarsImageModel>) : MarsImagesState()
}
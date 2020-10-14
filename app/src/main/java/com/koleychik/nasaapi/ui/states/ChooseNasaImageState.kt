package com.koleychik.nasaapi.ui.states

import com.koleychik.nasaapi.models.mainModels.NasaLibraryModel

sealed class ChooseNasaImageState {

//    object Loading : ChooseNasaImageState()
    class Error(val resText : Int) : ChooseNasaImageState()
    class Searching(val text: String) : ChooseNasaImageState()
    class Show(val list: List<NasaLibraryModel>) : ChooseNasaImageState()
    object Refreshing : ChooseNasaImageState()

}
package com.koleychik.nasaapi.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koleychik.nasaapi.models.mainModels.NasaLibraryModel
import com.koleychik.nasaapi.ui.states.ChooseNasaImageState

class ChooseNasaImageViewModel: ViewModel() {

    val state = MutableLiveData<ChooseNasaImageState>(ChooseNasaImageState.Searching(""))

    val searchingWord = MutableLiveData<String>()

    val list = MutableLiveData<List<NasaLibraryModel>>()

    val switchNumber = MutableLiveData(0)

    fun search(text : String? = searchingWord.value){

    }

}
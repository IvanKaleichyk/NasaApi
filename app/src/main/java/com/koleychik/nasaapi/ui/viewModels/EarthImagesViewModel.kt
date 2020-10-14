package com.koleychik.nasaapi.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koleychik.nasaapi.models.DateModel
import com.koleychik.nasaapi.models.mainModels.EarthImageModel
import com.koleychik.nasaapi.repositories.EarthImageRepository
import com.koleychik.nasaapi.ui.states.EarthImagesState
import com.koleychik.nasaapi.utils.errorResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EarthImagesViewModel: ViewModel() {

    private val repository = EarthImageRepository()

    val state = MutableLiveData<EarthImagesState>(EarthImagesState.Loading)

    val list = MutableLiveData<List<EarthImageModel>>()

    val dateModel = MutableLiveData<DateModel>()

    fun searching(date: String) = CoroutineScope(Dispatchers.IO).launch {
        val response = repository.getEarthImagesByDate(date)
        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                var newList = response.body()
                if (newList == null) newList = listOf()
                list.value = newList
            }
            else{
                state.value = EarthImagesState.Error(errorResponse(response.code()))
            }
        }
    }

}
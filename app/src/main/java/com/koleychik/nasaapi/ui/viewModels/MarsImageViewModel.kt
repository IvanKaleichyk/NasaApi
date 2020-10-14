package com.koleychik.nasaapi.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koleychik.nasaapi.models.DateModel
import com.koleychik.nasaapi.models.mainModels.MarsImageModel
import com.koleychik.nasaapi.repositories.MarsPhotosRepository
import com.koleychik.nasaapi.ui.states.MarsImagesState
import com.koleychik.nasaapi.utils.errorResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarsImageViewModel : ViewModel() {

    private val repository = MarsPhotosRepository()

    val state = MutableLiveData<MarsImagesState>(MarsImagesState.Loading)

    val list = MutableLiveData<List<MarsImageModel>>()

    val dateModel = MutableLiveData<DateModel>()

    fun searching(options: Map<String, String>) =
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getBy(options)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val marsImageModel = response.body()
                    var newList = listOf<MarsImageModel>()
                    if (marsImageModel != null) newList = marsImageModel.photos
                    list.value = newList
                } else state.value = MarsImagesState.Error(errorResponse(response.code()))
            }
        }
}
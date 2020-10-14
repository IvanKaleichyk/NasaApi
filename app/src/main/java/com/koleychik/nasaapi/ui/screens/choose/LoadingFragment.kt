package com.koleychik.nasaapi.ui.screens.choose

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.callbacks.CheckCallback
import com.koleychik.nasaapi.repositories.MarsPhotosRepository
import com.koleychik.nasaapi.ui.dialogs.DialogCheckInternetConnection
import com.koleychik.nasaapi.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialogCheckInternetConnection = DialogCheckInternetConnection(requireContext())

        dialogCheckInternetConnection.checkInternet(object : CheckCallback {
            override fun successful() {
                super.successful()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                    Navigation.findNavController(requireView()).navigate(R.id.mainMenuFragment)
                }
            }

            override fun failed() {
                super.failed()
            }
        })

        Navigation.findNavController(requireView()).navigate(R.id.mainMenuFragment)

    }
}
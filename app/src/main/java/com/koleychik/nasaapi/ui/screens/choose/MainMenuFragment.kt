package com.koleychik.nasaapi.ui.screens.choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.koleychik.nasaapi.R
import kotlinx.android.synthetic.main.fragment_main_menu.view.*

class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeOnClickListener()
    }

    private fun makeOnClickListener() {
        val onClickListener = View.OnClickListener {
            when (it.id) {
                R.id.marsImages -> Navigation.findNavController(requireView())
                    .navigate(R.id.action_mainMenuFragment_to_marsFragment)

                R.id.earthImages -> Navigation.findNavController(requireView())
                    .navigate(R.id.action_mainMenuFragment_to_earthImagesFragment)
            }
        }

        requireView().marsImages.setOnClickListener(onClickListener)
        requireView().earthImages.setOnClickListener(onClickListener)

    }
}
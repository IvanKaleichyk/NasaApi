package com.koleychik.nasaapi.ui.screens.choose

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.utils.VideoUtils
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

//                R.id.nasaImages -> Navigation.findNavController(requireView())
//                    .navigate(R.id.action_mainMenuFragment_to_nasaImagesFragment)
//
//                R.id.nasaVideo -> Navigation.findNavController(requireView())
//                    .navigate(R.id.action_mainMenuFragment_to_nasaVideosFragment)

                R.id.earthImages -> Navigation.findNavController(requireView())
                    .navigate(R.id.action_mainMenuFragment_to_earthImagesFragment)
            }
        }

        requireView().marsImages.setOnClickListener(onClickListener)
        requireView().earthImages.setOnClickListener(onClickListener)

    }

    private fun workWithVideoView() {
        val metrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(metrics)
        val display = requireActivity().windowManager.defaultDisplay
//        stageWidth = display.width
//        stageHeight = display.getHeigth()
        val params = requireView().video.layoutParams
        params.width = display.width
        params.height = display.height

        requireView().video.apply {
            layoutParams = params
            setVideoURI(VideoUtils.getUri(requireContext().packageName, R.raw.main_manu_bg_video))
            start()
        }
    }

    override fun onStart() {
        super.onStart()
        workWithVideoView()
        requireView().video.resume()
    }

    override fun onStop() {
        super.onStop()
        requireView().video.stopPlayback()
    }
}
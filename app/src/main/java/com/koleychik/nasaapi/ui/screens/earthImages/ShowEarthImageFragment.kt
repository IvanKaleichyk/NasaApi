package com.koleychik.nasaapi.ui.screens.earthImages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.models.mainModels.EarthImageModel
import com.koleychik.nasaapi.ui.adapters.ShowEarthImageAdapter
import com.koleychik.nasaapi.utils.Constants
import kotlinx.android.synthetic.main.fragment_show_image.view.*

class ShowEarthImageFragment : Fragment() {

    private lateinit var list : List<EarthImageModel>

    private lateinit var idSelect : String

    private val adapter = ShowEarthImageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tryGetArgs()

        makeViewPager()
    }

    private fun tryGetArgs() {
        idSelect = requireArguments().getString(Constants.EARTH_IMAGE_ID_SELECT_BUNDLE, "")
        list = tryGetList()
        adapter.submitList(list)
    }

    private fun tryGetList(): List<EarthImageModel> {
        return requireArguments().getParcelableArrayList(Constants.EARTH_IMAGE_LIST_BUNDLE)
            ?: listOf()
    }

    private fun makeViewPager() {
        requireView().imageViewPager.adapter = adapter
        requireView().imageViewPager.currentItem = getPosition()
    }

    private fun getPosition(): Int {
        for (i in (list.indices)) {
            if (list[i].identifier == idSelect) {
                return i
            }
        }
        return 0
    }

}
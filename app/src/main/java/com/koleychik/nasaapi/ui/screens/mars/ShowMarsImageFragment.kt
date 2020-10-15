package com.koleychik.nasaapi.ui.screens.mars


import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat

import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.models.mainModels.MarsImageModel
import com.koleychik.nasaapi.ui.adapters.MarsShowImageAdapter
import com.koleychik.nasaapi.utils.Constants
import kotlinx.android.synthetic.main.fragment_show_image.view.*

class ShowMarsImageFragment : Fragment() {

    private lateinit var list: List<MarsImageModel>

    private var idSelect = 0

    private val adapter = MarsShowImageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        return inflater.inflate(R.layout.fragment_show_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(requireView().imageViewPager, Constants.IMAGE_TRANSITION)
        tryGetArgs()

        makeViewPager()
    }

    private fun makeViewPager() {
        requireView().imageViewPager.adapter = adapter
        requireView().imageViewPager.currentItem = getPosition()
    }

    private fun tryGetList(): List<MarsImageModel> {
        return requireArguments().getParcelableArrayList(Constants.MARS_IMAGE_LIST_BUNDLE)
            ?: listOf()
    }

    private fun tryGetArgs() {
        idSelect = requireArguments().getInt(Constants.MARS_IMAGE_ID_SELECT_BUNDLE, 0)
        list = tryGetList()
        adapter.submitList(list)
    }

    private fun getPosition(): Int {
        for (i in (list.indices)) {
            if (list[i].id == idSelect) {
                return i
            }
        }
        return 0
    }

}
package com.koleychik.nasaapi.ui.screens.mars


import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
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

    private var idShowView = 0

//    private lateinit var adapter: ShowMarsImageViewPagerAdapter
    private val adapter = MarsShowImageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        sharedElementEnterTransition =
//            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return inflater.inflate(R.layout.fragment_show_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireView().title.visibility = View.GONE
//        ViewCompat.setTransitionName(requireView().imageViewPager, "transImg")
        tryGetArgs()

        if (list.isEmpty()) Log.d(Constants.TAG, "list is empty")
        else Log.d(Constants.TAG, "list[0].earth_date = ${list[0].earth_date}")

        makeViewPager()
        subscribe()
    }

    private fun makeViewPager() {
//        requireView().imageViewPager.apply {
//            this.adapter = adapter
//            currentItem = getPosition()
//        }
        requireView().imageViewPager.adapter = adapter
        requireView().imageViewPager.currentItem = getPosition()
    }

    private fun subscribe() {
//        adapter.title.observe(viewLifecycleOwner, {
//            if (it != null) requireView().title.text = it
//        })
//        adapter.isBind.observe(viewLifecycleOwner, {
//            if (it){
//                if (requireView().imageViewPager.currentItem != idShowView){
//                    idShowView = requireView().imageViewPager.currentItem
////                    requireView().title.text = list[idShowView].earth_date
//                }
//            }
//        })
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
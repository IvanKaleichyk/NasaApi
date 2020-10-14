package com.koleychik.nasaapi.ui.screens.mars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.data.listCameras
import com.koleychik.nasaapi.ui.adapters.MarsSelectCameraAdapter
import kotlinx.android.synthetic.main.fragment_mars_select_camera.view.*

class MarsSelectCameraFragment : Fragment() {

    private val adapter = MarsSelectCameraAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mars_select_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createRv()
    }

    private fun createRv(){
        requireView().rv.also {rv ->
            rv.setHasFixedSize(true)
            rv.layoutManager = GridLayoutManager(context, 2)
            adapter.submitList(listCameras)
            rv.adapter = adapter
        }
    }

}
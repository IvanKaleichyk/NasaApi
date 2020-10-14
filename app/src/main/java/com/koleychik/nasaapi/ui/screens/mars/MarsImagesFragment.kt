package com.koleychik.nasaapi.ui.screens.mars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.callbacks.ChooseDateCallback
import com.koleychik.nasaapi.models.DateModel
import com.koleychik.nasaapi.ui.adapters.MarsImageAdapter
import com.koleychik.nasaapi.ui.dialogs.ChooseDateDialog
import com.koleychik.nasaapi.ui.states.MarsImagesState
import com.koleychik.nasaapi.ui.viewModels.MarsImageViewModel
import com.koleychik.nasaapi.utils.Constants
import com.koleychik.nasaapi.utils.GetDateModel
import com.koleychik.nasaapi.utils.SharedPreferenceUtils
import com.koleychik.nasaapi.utils.StringUtils
import kotlinx.android.synthetic.main.fragment_mars_images.view.*
import kotlinx.android.synthetic.main.fragment_show_image.view.*

class MarsImagesFragment : Fragment() {

    private lateinit var viewModel: MarsImageViewModel

    private val adapter = MarsImageAdapter()

    private var camera = ""

    private lateinit var chooseDateDialog: ChooseDateDialog

    private lateinit var sharedPreferenceUtils: SharedPreferenceUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[MarsImageViewModel::class.java]
        return inflater.inflate(R.layout.fragment_mars_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferenceUtils = SharedPreferenceUtils(requireContext())
        chooseDateDialog = ChooseDateDialog(requireContext(), createChooseDateCallback())

        getArgs()
        getDate()
        makeOnCLickListener()
        makeRv()
        subscribe()
        createSwipeToRefresh()
    }

    private fun subscribe() {
        viewModel.state.observe(viewLifecycleOwner, { render(it) })
        viewModel.list.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            if (it.isEmpty()) viewModel.state.value =
                MarsImagesState.Error(R.string.cannotFoundImages)
            else viewModel.state.value = MarsImagesState.Show(it)
        })
        viewModel.dateModel.observe(viewLifecycleOwner, {
            requireView().header.text = StringUtils.getDateString(it, ".")
        })
    }

    private fun render(state: MarsImagesState) {
        when (state) {
            is MarsImagesState.Loading -> {
                requireView().rv.visibility = View.GONE
                requireView().progressBar.visibility = View.GONE
                requireView().LL_error.visibility = View.GONE
                requireView().progressBar.visibility = View.VISIBLE
                searching()
            }
            is MarsImagesState.Error -> {
                requireView().rv.visibility = View.GONE
                requireView().progressBar.visibility = View.GONE
                requireView().LL_error.visibility = View.VISIBLE
                requireView().textError.text = requireContext().getString(state.resText)
            }
            is MarsImagesState.Show -> {
                requireView().rv.visibility = View.VISIBLE
                requireView().progressBar.visibility = View.GONE
                requireView().LL_error.visibility = View.GONE
                requireView().swipeToRefresh.isRefreshing = false

                adapter.submitList(state.list)
            }
        }
    }

    private fun searching() {

        val date = viewModel.dateModel.value!!
        Log.d(Constants.TAG, "searching date = ${date.day} + ${date.month} + ${date.year}")

        val map = mapOf(
            "camera" to camera,
            "earth_date" to StringUtils.getDateString(date, "-")
        )
        viewModel.searching(map)
    }

    private fun makeRv() {
        requireView().rv.layoutManager = GridLayoutManager(requireContext(), 2)
        requireView().rv.adapter = adapter
    }

    private fun makeOnCLickListener() {
        val onClickListener = View.OnClickListener {
            when (it.id) {
                R.id.header -> {
                    val dateModel = viewModel.dateModel.value!!
                    chooseDateDialog.show(
                        day = dateModel.day.toInt(),
                        month = dateModel.month.toInt(),
                        year = dateModel.year.toInt(),
                    )
                }
            }
        }

        requireView().header.setOnClickListener(onClickListener)
    }

    private fun getDate() {
        if (viewModel.dateModel.value == null) {
            val textShared = sharedPreferenceUtils.getString(Constants.DATA_MARS_IMAGES)
            if (textShared == null) viewModel.dateModel.value = GetDateModel.get2015()
            else viewModel.dateModel.value = GetDateModel.getDateModelFromString(textShared, ".")
        }
    }

    private fun createSwipeToRefresh() {
        requireView().swipeToRefresh.setOnRefreshListener {
            requireView().swipeToRefresh.isRefreshing = true
            viewModel.state.value = MarsImagesState.Loading
        }
    }

    private fun getArgs() {
        camera = requireArguments().getString(Constants.MARS_CAMERA_BUNDLE, "FHAZ")
    }

    private fun createChooseDateCallback() = object : ChooseDateCallback {
        override fun start(day: String, month: String, year: String) {
            viewModel.dateModel.value = DateModel(day, month, year)
            viewModel.state.value = MarsImagesState.Loading
        }
    }

    override fun onStop() {
        super.onStop()
        sharedPreferenceUtils.saveString(
            key = Constants.DATA_MARS_IMAGES,
            value = requireView().title.text.toString()
        )
    }
}
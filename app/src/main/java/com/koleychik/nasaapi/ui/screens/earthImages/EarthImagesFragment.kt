package com.koleychik.nasaapi.ui.screens.earthImages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.callbacks.ChooseDateCallback
import com.koleychik.nasaapi.models.DateModel
import com.koleychik.nasaapi.ui.adapters.EarthImagesAdapter
import com.koleychik.nasaapi.ui.dialogs.ChooseDateDialog
import com.koleychik.nasaapi.ui.states.EarthImagesState
import com.koleychik.nasaapi.ui.viewModels.EarthImagesViewModel
import com.koleychik.nasaapi.utils.*
import kotlinx.android.synthetic.main.fragment_mars_images.view.*
import kotlinx.android.synthetic.main.fragment_show_image.view.*

class EarthImagesFragment : Fragment() {

    private lateinit var viewModel: EarthImagesViewModel

    private val adapter = EarthImagesAdapter()

    private lateinit var sharedPreferenceUtils : SharedPreferenceUtils

    private lateinit var chooseDateDialog: ChooseDateDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[EarthImagesViewModel::class.java]
        return inflater.inflate(R.layout.fragment_mars_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferenceUtils = SharedPreferenceUtils(requireContext())
        chooseDateDialog = ChooseDateDialog(requireContext(), createChooseDateCallback())

        getDate()
        makeOnCLickListener()
        makeRV()
        subscribe()
        createSwipeToRefresh()
    }

    private fun makeOnCLickListener(){
        val onClickListener = View.OnClickListener{
            when(it.id){
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

    private fun subscribe() {
        viewModel.state.observe(viewLifecycleOwner, { render(it) })
        viewModel.list.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            if (it.isEmpty()) {
                viewModel.state.value = EarthImagesState.Error(R.string.cannotFoundImages)
            } else viewModel.state.value = EarthImagesState.Show(it)
        })
        viewModel.dateModel.observe(viewLifecycleOwner, {
            viewModel.state.value = EarthImagesState.Loading
        })
    }

    private fun render(state: EarthImagesState) {
        when (state) {
            is EarthImagesState.Loading -> {
                requireView().rv.visibility = View.GONE
                requireView().progressBar.visibility = View.GONE
                requireView().LL_error.visibility = View.VISIBLE
                requireView().progressBar.visibility = View.VISIBLE
                searching()
            }
            is EarthImagesState.Error -> {
                requireView().rv.visibility = View.GONE
                requireView().progressBar.visibility = View.GONE
                requireView().LL_error.visibility = View.VISIBLE
                requireView().textError.text = requireContext().getString(state.resText)
            }
            is EarthImagesState.Show -> {
                requireView().rv.visibility = View.VISIBLE
                requireView().progressBar.visibility = View.GONE
                requireView().LL_error.visibility = View.GONE
                requireView().swipeToRefresh.isRefreshing = false

                adapter.submitList(state.list)
            }
        }
    }

    private fun makeRV() {
        requireView().rv.adapter = adapter
        requireView().rv.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun getDate() {
        if (viewModel.dateModel.value == null) {
            val textShared = sharedPreferenceUtils.getString(Constants.DATA_MARS_IMAGES)
            if (textShared == null) viewModel.dateModel.value = GetDateModel.get2015()
            else viewModel.dateModel.value = GetDateModel.getDateModelFromString(textShared, ".")
        }
    }

    private fun searching() {
        viewModel.searching(StringUtils.getDateString(viewModel.dateModel.value!!, "/"))
    }

    private fun createChooseDateCallback() = object : ChooseDateCallback {
        override fun start(day: String, month: String, year: String) {
            viewModel.dateModel.value = DateModel(day, month, year)
            viewModel.state.value = EarthImagesState.Loading
        }
    }

    override fun onStop() {
        super.onStop()
        sharedPreferenceUtils.saveString(
            key = Constants.DATA_MARS_IMAGES,
            value = requireView().title.text.toString()
        )
    }

    private fun createSwipeToRefresh() {
        requireView().swipeToRefresh.setOnRefreshListener {
            requireView().swipeToRefresh.isRefreshing = true
            viewModel.state.value = EarthImagesState.Loading
        }
    }
}
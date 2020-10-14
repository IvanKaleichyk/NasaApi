package com.koleychik.nasaapi.ui.screens.nasaImages

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.ui.adapters.NasaLibraryAdapter
import com.koleychik.nasaapi.ui.states.ChooseNasaImageState
import com.koleychik.nasaapi.ui.viewModels.ChooseNasaImageViewModel
import com.koleychik.nasaapi.utils.switchList
import kotlinx.android.synthetic.main.dialog_search.*
import kotlinx.android.synthetic.main.fragment_choose_image.view.*

class ChooseNasaImageFragment : Fragment() {

    private val adapter = NasaLibraryAdapter()

    private lateinit var viewModel: ChooseNasaImageViewModel

    private val dialog = Dialog(requireContext())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ChooseNasaImageViewModel::class.java]

        makeSwitch()
        createDialogSearch()
        createSwipeToRefresh()
        createOnClickListener()
        subscribe()
        makeRV()
        return inflater.inflate(R.layout.fragment_choose_image, container, false)
    }

    private fun subscribe() {
        viewModel.state.observe(viewLifecycleOwner, { render(it) })

        viewModel.list.observe(viewLifecycleOwner, {
            if (it == null) {
                viewModel.state.value = ChooseNasaImageState.Error(R.string.cannotFoundImages)
            } else viewModel.state.value = ChooseNasaImageState.Show(it)
        })
    }

    private fun makeRV() {
        requireView().rv.adapter = adapter
    }

    private fun render(state: ChooseNasaImageState) {
        when (state) {
            is ChooseNasaImageState.Searching -> {
                requireView().rv.visibility = View.GONE
                requireView().progressBar.visibility = View.VISIBLE
                viewModel.search()
            }
            is ChooseNasaImageState.Refreshing -> {
                requireView().swipeToRefresh.isRefreshing = true
                viewModel.search()
            }
            is ChooseNasaImageState.Error -> {
                requireView().LL_error.visibility = View.VISIBLE
                requireView().textError.setText(state.resText)
            }
            is ChooseNasaImageState.Show -> {
                requireView().LL_error.visibility = View.GONE
                requireView().rv.visibility = View.GONE
                requireView().progressBar.visibility = View.VISIBLE
                requireView().swipeToRefresh.isRefreshing = false
                adapter.submitList(switchList(state.list, viewModel.switchNumber.value!!))
            }
        }
    }

    private fun createSwipeToRefresh() {
        requireView().swipeToRefresh.setOnRefreshListener {
            viewModel.state.value = ChooseNasaImageState.Refreshing
        }
    }

    private fun makeSwitch() {
        val onCLickListener = View.OnClickListener {
            val lastSwitchNumber = viewModel.switchNumber.value!!
            when (it.id) {
                R.id.switchAll -> {
                    setStyleForSwitchElement(
                        requireView().switchAll,
                        requireView().switchImage,
                        requireView().switchVideo
                    )
                    viewModel.switchNumber.value = 0
                }
                R.id.switchImage -> {
                    setStyleForSwitchElement(
                        requireView().switchImage,
                        requireView().switchAll,
                        requireView().switchVideo
                    )
                    viewModel.switchNumber.value = 1
                }
                R.id.switchVideo -> {
                    setStyleForSwitchElement(
                        requireView().switchVideo,
                        requireView().switchImage,
                        requireView().switchAll
                    )
                    viewModel.switchNumber.value = 2
                }
            }
            val list = viewModel.list.value
            if (list != null) {
                if (viewModel.switchNumber.value!! == lastSwitchNumber) return@OnClickListener
                else viewModel.state.value = ChooseNasaImageState.Show(
                    switchList(
                        list = list,
                        switchNumber = viewModel.switchNumber.value!!
                    )
                )
            }
        }
        requireView().switchAll.setOnClickListener(onCLickListener)
        requireView().switchImage.setOnClickListener(onCLickListener)
        requireView().switchVideo.setOnClickListener(onCLickListener)
    }

    private fun createOnClickListener(){
        val onClickListener = View.OnClickListener{
            when(it.id){
                R.id.searchImage -> dialog.show()
            }
        }

        requireView().searchImage.setOnClickListener(onClickListener)
    }

    private fun createDialogSearch(){
        dialog.setContentView(R.layout.dialog_search)
        dialog.setCanceledOnTouchOutside(false)
        dialog.btnSearch.setOnClickListener {
            viewModel.search(dialog.edtKeyWords.text.toString().trim())
            dialog.dismiss()
        }
    }

    private fun setStyleForSwitchElement(vHidden1: Button, vHidden2: Button, vOpen: Button) {
        vHidden1.setBackgroundResource(R.drawable.bg_btn_switch_dont_select)
        vHidden2.setBackgroundResource(R.drawable.bg_btn_switch_dont_select)
        vOpen.setBackgroundResource(R.drawable.bg_btn_switch_select)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            vHidden1.setTextColor(resources.getColor(R.color.colorMain, resources.newTheme()))
            vHidden2.setTextColor(resources.getColor(R.color.colorMain, resources.newTheme()))
            vOpen.setTextColor(resources.getColor(R.color.colorBlack, resources.newTheme()))
        }

        vHidden2.setBackgroundResource(R.drawable.bg_btn_switch_dont_select)

        vOpen.setBackgroundResource(R.drawable.bg_btn_switch_select)
    }

}
package com.koleychik.nasaapi.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import coil.load
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.models.mainModels.NasaLibraryModel
import com.koleychik.nasaapi.utils.Constants
import com.koleychik.nasaapi.utils.StringUtils
import kotlinx.android.synthetic.main.item_rv_image.view.*

class NasaLibraryAdapter : RecyclerView.Adapter<NasaLibraryAdapter.MainViewHolder>() {

    private val sortedList: SortedList<NasaLibraryModel>

    init {
        sortedList = SortedList(
            NasaLibraryModel::class.java,
            object : SortedListAdapterCallback<NasaLibraryModel>(this) {
                override fun compare(o1: NasaLibraryModel, o2: NasaLibraryModel): Int = 1

                override fun areContentsTheSame(
                    oldItem: NasaLibraryModel,
                    newItem: NasaLibraryModel
                ): Boolean =
                    oldItem == newItem

                override fun areItemsTheSame(
                    item1: NasaLibraryModel,
                    item2: NasaLibraryModel
                ): Boolean =
                    item1.data.nasa_id == item2.data.nasa_id
            })
    }

    fun submitList(newList: List<NasaLibraryModel>) {
        sortedList.clear()
        sortedList.addAll(newList)
    }

    fun addToList(value: NasaLibraryModel) {
        sortedList.add(value)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: NasaLibraryModel) {
            itemView.setOnClickListener(createOnClickListener(model))
            itemView.image.load(model.href)
            itemView.description.text = StringUtils.maxLength(model.data.title, Constants.maxStringSize)
        }

        private fun createOnClickListener(model: NasaLibraryModel) = View.OnClickListener {
            val bundle = Bundle()
            var isImage = false
            if (model.data.media_type == "image") isImage = true

            bundle.putBoolean(Constants.IS_IMAGE_BUNDLE, isImage)
            bundle.putString(Constants.URL_BUNDLE, model.href)
            Navigation.findNavController(itemView).navigate(
                R.id.action_chooseNasaImageFragment_to_showNasaLibraryFragment,
                bundle
            )
        }
    }
}
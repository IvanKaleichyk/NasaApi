package com.koleychik.nasaapi.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.models.CameraModel
import com.koleychik.nasaapi.utils.Constants
import kotlinx.android.synthetic.main.item_rv_text.view.*

class MarsSelectCameraAdapter : RecyclerView.Adapter<MarsSelectCameraAdapter.MainViewHolder>() {

    private val sortedList: SortedList<CameraModel>

    init {
        sortedList = SortedList(
            CameraModel::class.java,
            object : SortedListAdapterCallback<CameraModel>(this) {
                override fun compare(o1: CameraModel, o2: CameraModel) = 1

                override fun areContentsTheSame(oldItem: CameraModel, newItem: CameraModel): Boolean =
                    oldItem.fullName == newItem.fullName

                override fun areItemsTheSame(item1: CameraModel, item2: CameraModel): Boolean =
                    item1 == item2
            })
    }

    fun submitList(newList: List<CameraModel>) {
        sortedList.clear()
        sortedList.addAll(newList)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsSelectCameraAdapter.MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_text, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: CameraModel) {
            itemView.description.visibility = View.GONE
            itemView.text.text = model.fullName

            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(Constants.MARS_CAMERA_BUNDLE, model.abbreviation)
                Navigation.findNavController(itemView).navigate(
                    R.id.action_marsFragment_to_marsImagesFragment,
                    bundle
                )
            }
        }

    }
}
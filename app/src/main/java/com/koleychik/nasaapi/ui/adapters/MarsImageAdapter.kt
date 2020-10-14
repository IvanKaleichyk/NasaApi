package com.koleychik.nasaapi.ui.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import coil.load
import com.bumptech.glide.Glide
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.models.mainModels.MarsImageModel
import com.koleychik.nasaapi.utils.Constants
import kotlinx.android.synthetic.main.item_rv_image.view.*

class MarsImageAdapter : RecyclerView.Adapter<MarsImageAdapter.MainViewHolder>() {

    private val sortedList: SortedList<MarsImageModel>

    init {
        sortedList = SortedList(
            MarsImageModel::class.java,
            object : SortedListAdapterCallback<MarsImageModel>(this) {
                override fun compare(o1: MarsImageModel, o2: MarsImageModel): Int = 1

                override fun areContentsTheSame(
                    oldItem: MarsImageModel,
                    newItem: MarsImageModel
                ): Boolean =
                    oldItem.id == newItem.id

                override fun areItemsTheSame(
                    item1: MarsImageModel,
                    item2: MarsImageModel
                ): Boolean =
                    item1 == item2
            })
    }

    fun submitList(newList: List<MarsImageModel>) {
        sortedList.clear()
        sortedList.addAll(newList)
    }

    fun addToList(value: MarsImageModel) {
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

    private fun getArrayList() : ArrayList<MarsImageModel>{
        val resList = arrayListOf<MarsImageModel>()
        for (i in (0 until sortedList.size())) resList.add(sortedList[i])
        return resList
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: MarsImageModel) {
            Log.d(Constants.TAG, "bind model.earth_date = ${model.earth_date}")
            itemView.description.text = model.earth_date
//            Glide.with(itemView.context).load(model.img_src).into(itemView.image)
            itemView.image.load(model.img_src)

            Log.d(Constants.TAG, "model.img_src = ${model.img_src}")

            itemView.image.transitionName = Constants.IMAGE_TRANSITION
            itemView.setOnClickListener(makeOnCLickListener(model))
        }

        private fun makeOnCLickListener(model : MarsImageModel) = View.OnClickListener {
            val extras = FragmentNavigatorExtras(
                itemView.image to Constants.IMAGE_TRANSITION
            )

            val bundle = Bundle()
            bundle.putParcelableArrayList(Constants.MARS_IMAGE_LIST_BUNDLE, getArrayList())
            bundle.putString(Constants.IMAGE_BUNDLE, model.img_src)
            Navigation.findNavController(itemView).navigate(
                R.id.action_marsImagesFragment_to_showImageFragment,
                bundle,
                null,
                extras
            )
        }
    }
}
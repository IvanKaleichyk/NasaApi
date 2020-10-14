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
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.models.mainModels.EarthImageModel
import com.koleychik.nasaapi.utils.Constants
import kotlinx.android.synthetic.main.item_rv_image.view.*

class EarthImagesAdapter : RecyclerView.Adapter<EarthImagesAdapter.MainViewHolder>() {

    private val sortedList: SortedList<EarthImageModel>

    init {
        sortedList = SortedList(
            EarthImageModel::class.java,
            object : SortedListAdapterCallback<EarthImageModel>(this) {
                override fun compare(o1: EarthImageModel, o2: EarthImageModel): Int = 1

                override fun areContentsTheSame(oldItem: EarthImageModel, newItem: EarthImageModel):
                        Boolean = oldItem == newItem

                override fun areItemsTheSame(item1: EarthImageModel, item2: EarthImageModel):
                        Boolean = item1 == item2
            })
    }

    fun submitList(newList: List<EarthImageModel>) {
        sortedList.clear()
        sortedList.addAll(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()


    private fun getArrayList() : ArrayList<EarthImageModel>{
        val resList = arrayListOf<EarthImageModel>()
        for (i in (0 until sortedList.size())) resList.add(sortedList[i])
        return resList
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: EarthImageModel) {
            Log.d(Constants.TAG, "bind model.earth_date = ${model.image}")
            itemView.description.text = model.caption
//            Glide.with(itemView.context).load(model.img_src).into(itemView.image)
            itemView.image.load(model.image)

            Log.d(Constants.TAG, "model.img_src = ${model.image}")

            itemView.image.transitionName = Constants.IMAGE_TRANSITION
            itemView.setOnClickListener(makeOnCLickListener(model))
        }

        private fun makeOnCLickListener(model : EarthImageModel) = View.OnClickListener {
            val extras = FragmentNavigatorExtras(
                itemView.image to Constants.IMAGE_TRANSITION
            )

            val bundle = Bundle()
            bundle.putParcelableArrayList(Constants.MARS_IMAGE_LIST_BUNDLE, getArrayList())
            bundle.putString(Constants.IMAGE_BUNDLE, model.image)
            Navigation.findNavController(itemView).navigate(
                R.id.action_marsImagesFragment_to_showImageFragment,
                bundle,
                null,
                extras
            )
        }

    }
}
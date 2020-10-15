package com.koleychik.nasaapi.ui.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.models.mainModels.EarthImageModel
import kotlinx.android.synthetic.main.item_rv_show_mars_image.view.*

class ShowEarthImageAdapter : RecyclerView.Adapter<ShowEarthImageAdapter.MainViewHolder>() {

    private val sortedList: SortedList<EarthImageModel>


    init {
        sortedList = SortedList(
            EarthImageModel::class.java,
            object : SortedListAdapterCallback<EarthImageModel>(this) {
                override fun compare(o1: EarthImageModel, o2: EarthImageModel): Int = 1

                override fun areContentsTheSame(
                    oldItem: EarthImageModel,
                    newItem: EarthImageModel
                ): Boolean =
                    oldItem.identifier == newItem.identifier

                override fun areItemsTheSame(
                    item1: EarthImageModel,
                    item2: EarthImageModel
                ): Boolean =
                    item1 == item2
            })
    }

    fun submitList(newList: List<EarthImageModel>) {
        sortedList.clear()
        sortedList.addAll(newList)
    }

    override fun onViewRecycled(holder: MainViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.itemView.context).clear(holder.itemView.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_show_mars_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: EarthImageModel) {
            loadImg(model)

        }

        private fun loadImg(model: EarthImageModel) {
            Glide.with(itemView.context)
                .asBitmap()
                .load(model.image)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        itemView.img.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
        }

    }

}
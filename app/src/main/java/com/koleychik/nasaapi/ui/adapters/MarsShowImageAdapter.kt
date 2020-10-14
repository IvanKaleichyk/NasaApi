package com.koleychik.nasaapi.ui.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.models.mainModels.MarsImageModel
import kotlinx.android.synthetic.main.item_rv_show_mars_image.view.*

class MarsShowImageAdapter : RecyclerView.Adapter<MarsShowImageAdapter.MainViewHolder>() {

    private val sortedList: SortedList<MarsImageModel>

    val isBind =  MutableLiveData<Boolean>()

    init {
        sortedList = SortedList(
            MarsImageModel::class.java,
            object : SortedListAdapterCallback<MarsImageModel>(this) {
                override fun compare(o1: MarsImageModel, o2: MarsImageModel): Int = 1

                override fun areContentsTheSame(oldItem: MarsImageModel, newItem: MarsImageModel): Boolean =
                    oldItem.id == newItem.id

                override fun areItemsTheSame(item1: MarsImageModel, item2: MarsImageModel): Boolean =
                    item1 == item2
            })
    }

    fun submitList(newList: List<MarsImageModel>) {
        sortedList.clear()
        sortedList.addAll(newList)
    }

    fun addToList(value : MarsImageModel){
        sortedList.add(value)
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
        isBind.value = true
        holder.bind(sortedList[position])
        isBind.value = false
    }

    override fun getItemCount(): Int = sortedList.size()

    inner class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bind(model : MarsImageModel){
            loadImg(model)

        }

        private fun loadImg(model: MarsImageModel) {
            Glide.with(itemView.context)
                .asBitmap()
                .load(model.img_src)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        itemView.img.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
        }

    }

}
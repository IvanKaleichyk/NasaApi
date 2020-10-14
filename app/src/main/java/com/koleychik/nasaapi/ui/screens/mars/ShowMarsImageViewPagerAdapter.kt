package com.koleychik.nasaapi.ui.screens.mars

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.PagerAdapter
import coil.clear
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.models.mainModels.MarsImageModel
import com.koleychik.nasaapi.utils.Constants
import kotlinx.android.synthetic.main.item_rv_show_mars_image.view.*

class ShowMarsImageViewPagerAdapter(private val list: List<MarsImageModel>) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun getCount(): Int {
        Log.d(Constants.TAG, "list.size = ${list.size}")
        return list.size
    }

    val title = MutableLiveData<String>()

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(container.context)
        val root = layoutInflater.inflate(R.layout.item_rv_show_mars_image, container, false)

        val model = list[position]

        title.value = model.earth_date

        Glide.with(root.context)
            .asBitmap()
            .load(model.img_src)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Log.d(Constants.TAG, "start create drawable url = ${model.img_src}")
                    root.img.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
        container.addView(root, 0)
        return root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val root = `object` as View
        root.img.clear()
        container.removeView(root)
    }
}
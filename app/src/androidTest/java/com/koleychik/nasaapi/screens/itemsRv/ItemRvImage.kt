package com.koleychik.nasaapi.screens.itemsRv

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.koleychik.nasaapi.R
import org.hamcrest.Matcher

class ItemRvImage(parent: Matcher<View>) : KRecyclerItem<ItemRvImage>(parent) {
    val image = KImageView(parent) { withId(R.id.image) }
}

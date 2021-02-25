package com.koleychik.nasaapi.screens.itemsRv

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.text.KTextView
import com.koleychik.nasaapi.R
import org.hamcrest.Matcher

class ItemRvText(parent: Matcher<View>) : KRecyclerItem<ItemRvText>(parent) {
    val text = KTextView(parent) { withId(R.id.text) }
}

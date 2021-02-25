package com.koleychik.nasaapi.screens

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.swiperefresh.KSwipeRefreshLayout
import com.agoda.kakao.text.KTextView
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.screens.itemsRv.ItemRvImage

class MarsImagesScreen : Screen<MarsImagesScreen>() {

    val header = KTextView { withId(R.id.header) }
    val swipeToRefresh = KSwipeRefreshLayout { withId(R.id.swipeToRefresh) }
    val rv = KRecyclerView({ withId(R.id.rv) }, itemTypeBuilder = { itemType(::ItemRvImage) })
    val progressBar = KView{withId(R.id.progressBar)}
    val LL_error = KView{withId(R.id.LL_error)}
    val textError = KTextView{withId(R.id.textError)}
}
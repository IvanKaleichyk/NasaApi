package com.koleychik.nasaapi.screens

import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.screens.itemsRv.ItemRvText

open class MarsSelectCameraScreen : Screen<MarsSelectCameraScreen>() {

    val rv = KRecyclerView({
        withId(R.id.rv)
    }, itemTypeBuilder = {
        itemType(::ItemRvText)
    })
}
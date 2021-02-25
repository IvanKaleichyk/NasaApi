package com.koleychik.nasaapi.screens

import com.agoda.kakao.image.KImageView
import com.agoda.kakao.pager.KViewPager
import com.agoda.kakao.screen.Screen
import com.koleychik.nasaapi.R

class ShowMarsImageScreen : Screen<ShowMarsImageScreen>() {

    val viewPager = KViewPager{withId(R.id.imageViewPager)}

    val img = KImageView{withId(R.id.img)}

}
package com.koleychik.nasaapi.screens

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.koleychik.nasaapi.R

open class MainMenuScreen: Screen<MainMenuScreen>() {

    val mainLL = KView{withId(R.id.mainLL)}
    val marsImages = KButton{withId(R.id.marsImages)}
    val earthImages = KButton{withId(R.id.earthImages)}
    val image1 = KImageView{withId(R.id.image1)}
    val image2 = KImageView{withId(R.id.image2)}
    val image3 = KImageView{withId(R.id.image3)}
}
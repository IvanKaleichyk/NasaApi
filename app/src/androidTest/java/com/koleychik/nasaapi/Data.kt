package com.koleychik.nasaapi

import com.koleychik.nasaapi.models.mainModels.MarsImageModel

fun getListMarsModels() : ArrayList<MarsImageModel>{
    val list = ArrayList<MarsImageModel>()
    list.add(MarsImageModel(10, 40, "http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631150503675E02_DXXX.jpg", "2015-05-30"))
    list.add(MarsImageModel(9, 40, "http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631140503674E01_DXXX.jpg", "2015-05-30"))
    list.add(MarsImageModel(11, 40, "http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631140305211E02_DXXX.jpg", "2015-05-30"))
    return list
}

package com.koleychik.nasaapi.utils

import android.net.Uri

class VideoUtils {

    companion object {
        fun getUri(packageName: String, videoRes: Int): Uri {
            return Uri.parse("android.resource://${packageName}/$videoRes")
        }
    }

}
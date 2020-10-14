package com.koleychik.nasaapi.utils

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedPreferenceUtils(private val context: Context) {

    private val sPref =
        context.getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) = CoroutineScope(Dispatchers.IO).launch {
        val editor = sPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defValue: String? = null) = sPref.getString(key, defValue)

}
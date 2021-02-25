package com.koleychik.nasaapi.testing

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.getListMarsModels
import com.koleychik.nasaapi.launchFragment
import com.koleychik.nasaapi.screens.ShowMarsImageScreen
import com.koleychik.nasaapi.ui.screens.activities.MainActivity
import com.koleychik.nasaapi.utils.Constants
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection
import java.net.URL


@RunWith(AndroidJUnit4::class)
class ShowMarsImageTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = ShowMarsImageScreen()

    private val startPage = 0
    var nowPage = startPage

    private val list = getListMarsModels()

    private val context: Context = useAppContext()

    @Test
    fun testViewPager() {
        val bundle = Bundle()
        bundle.putInt(Constants.MARS_IMAGE_ID_SELECT_BUNDLE, startPage)
        bundle.putParcelableArrayList(Constants.MARS_IMAGE_LIST_BUNDLE, list)

        launchFragment(rule, R.id.showImageFragment, bundle)
        screen.viewPager {
            isVisible()
//            isAtPage(startPage)
//
//            if (list.size - nowPage > 0){
//                nowPage++
//                swipeRight()
//                isAtPage(nowPage)
//            }
//            if (nowPage > 0){
//                nowPage--
//                swipeLeft()
//                isAtPage(nowPage)
//            }
        }

        screen.img {
            isVisible()

            val url = list[nowPage].img_src

            val x: Bitmap

            val connection = URL(url).openConnection() as HttpURLConnection
            connection.connect()
            val input = connection.inputStream

            x = BitmapFactory.decodeStream(input)
            val drawable = BitmapDrawable(Resources.getSystem(), x)

            hasDrawable(drawable)

//            val file = File(uri)
//
//            if (!file.exists()) idle(15000)
//
//            try {
//                val inputStream: InputStream = context.contentResolver.openInputStream(uri)!!
//                val yourDrawable = Drawable.createFromStream(inputStream, uri.toString())
//            } catch (e: FileNotFoundException) {
//                yourDrawable = getResources().getDrawable(R.drawable.default_image)
//            }

//            context.resources.

//            Glide.with(context)
//                .asDrawable()
//                .load(list[nowPage])
//                .into(object : CustomTarget<Drawable>() {
//                    override fun onResourceReady(
//                        resource: Drawable,
//                        transition: Transition<in Drawable>?
//                    ) {
//                        screen.img.hasDrawable(resource)
//                        idle(8000)
//                    }
//
//                    override fun onLoadCleared(placeholder: Drawable?) {}
//                })


        }

    }


    //    @Test
    private fun useAppContext(): Context {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.koleychik.nasaapi", appContext.packageName)
        return appContext
    }

}
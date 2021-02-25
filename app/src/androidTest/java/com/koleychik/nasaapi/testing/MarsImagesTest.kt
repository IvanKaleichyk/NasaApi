package com.koleychik.nasaapi.testing

import android.util.Log
import androidx.test.espresso.NoMatchingViewException
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.agoda.kakao.screen.Screen.Companion.idle
import com.koleychik.nasaapi.data.listCameras
import com.koleychik.nasaapi.screens.MainMenuScreen
import com.koleychik.nasaapi.screens.MarsImagesScreen
import com.koleychik.nasaapi.screens.MarsSelectCameraScreen
import com.koleychik.nasaapi.screens.dialogs.ChooseDateDialogScreen
import com.koleychik.nasaapi.screens.itemsRv.ItemRvImage
import com.koleychik.nasaapi.screens.itemsRv.ItemRvText
import com.koleychik.nasaapi.ui.screens.activities.MainActivity
import junit.framework.AssertionFailedError
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MarsImagesTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    private val mainMenuScreen = MainMenuScreen()
    private val marsSelectCameraScreen = MarsSelectCameraScreen()
    private val chooseDateDialogScreen = ChooseDateDialogScreen()
    private val screen = MarsImagesScreen()

    private fun goToScreen(cameraValue: Int) {
        try {
            mainMenuScreen.marsImages.click()
        } catch (e: NoMatchingViewException) {
        }

        marsSelectCameraScreen.rv.childAt<ItemRvText>(cameraValue) { text.click() }
    }

    @Test
    fun testHeader() {
        goToScreen(0)
        screen.header.click()
        chooseDateDialogScreen.btnSearch {
            isVisible()
            isEnabled()
        }
    }

    @Test
    fun testRV() {
        goToScreen(0)

        screen.progressBar.isVisible()

        idle(5000)

        screen.progressBar.isGone()

        for (i in (listCameras.indices)) {
            val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            mDevice.pressBack()

            Log.d("TEST_TAG", "i = $i")

            goToScreen(i)
            idle(1500)
            screen.rv {
                try {
                    isVisible()
                    childAt<ItemRvImage>(0) {
                        image {
                            isVisible()
                        }
                    }
                } catch (e: AssertionFailedError) {
                    testError()
                }
            }
        }
    }

    private fun testError() {
        screen.textError {
            isVisible()
            hasAnyText()
        }
    }

}
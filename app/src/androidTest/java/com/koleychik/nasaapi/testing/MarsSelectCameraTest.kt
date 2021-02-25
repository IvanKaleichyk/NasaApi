package com.koleychik.nasaapi.testing

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.koleychik.nasaapi.data.listCameras
import com.koleychik.nasaapi.screens.MainMenuScreen
import com.koleychik.nasaapi.screens.MarsSelectCameraScreen
import com.koleychik.nasaapi.screens.itemsRv.ItemRvText
import com.koleychik.nasaapi.ui.screens.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MarsSelectCameraTest {
    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val mainMenuScreen = MainMenuScreen()
    val screen = MarsSelectCameraScreen()

    @Test
    fun testRv() {
        mainMenuScreen { marsImages.click() }

        screen {
            rv {
                for (i in (listCameras.indices)) {
                    hasSize(listCameras.size)
                    childAt<ItemRvText>(i) {
                        text {
                            isVisible()
                            hasText(listCameras[i].fullName)
                        }
                    }
                }
            }
        }

    }

}
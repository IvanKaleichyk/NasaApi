package com.koleychik.nasaapi.testing

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.screens.MainMenuScreen
import com.koleychik.nasaapi.ui.screens.activities.MainActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainMenuTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = MainMenuScreen()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.koleychik.nasaapi", appContext.packageName)
    }

    @Test
    fun testImages() {
        screen {
            mainLL { hasBackgroundColor(R.color.colorMainBackground) }
            image3 {
                isVisible()
                hasDrawable(R.drawable.satellite_icon128)
            }
            image1 {
                isVisible()
                hasDrawable(R.drawable.ic_astronaut)
            }
            image2 {
                isVisible()
                hasDrawable(R.drawable.ic_observatory_svg)
            }
        }
    }

    @Test
    fun testBtn(){
        screen{
            marsImages{
                isVisible()
                isEnabled()
            }

            earthImages{
                isVisible()
                isEnabled()
            }

        }
    }

}
package com.example.uitestingtoastmessages

import android.os.IBinder
import android.view.WindowManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.*
import org.hamcrest.Description
import org.junit.Assert.*

import org.junit.*
import org.junit.runner.*

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get : Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun showToastClicked() {

        onView(withId(R.id.MainActivity_btn)).perform(click())


        onView(withText(R.string.toast_message))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))



    }




    inner class ToastMatcher : TypeSafeMatcher<Root?>() {

        //For debugging, telling us what the test is doing
        override fun describeTo(description: Description?) {
            description?.appendText("is toast")
        }

        override fun matchesSafely(item: Root?): Boolean {

            //Get the type of view
            val type: Int? = item?.getWindowLayoutParams()?.get()?.type

            //CHeck if the type of view matches a toast.
            if (type == WindowManager.LayoutParams.TYPE_TOAST) {


                //check if the window token matches with the app token
                //this determins that the toast is displayed and not blocked by any views in front of it
                val windowToken: IBinder = item.getDecorView().getWindowToken()
                val appToken: IBinder = item.getDecorView().getApplicationWindowToken()
                if (windowToken === appToken) { // means this window isn't contained by any other windows.
                    return true
                }
            }
            return false
        }
    }
}
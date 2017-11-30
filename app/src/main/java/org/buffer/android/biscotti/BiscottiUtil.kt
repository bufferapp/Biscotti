package org.buffer.android.biscotti

import android.content.pm.ActivityInfo
import android.os.SystemClock
import android.support.test.espresso.Espresso
import android.support.test.rule.ActivityTestRule

object BiscottiUtil {

    fun changeOrientationToLandscape(activity: ActivityTestRule<*>) {
        activity.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    fun changeOrientationToPortrait(activity: ActivityTestRule<*>) {
        activity.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun closeSoftKeyboardWithDelay(delay: Long) {
        Espresso.closeSoftKeyboard()
        SystemClock.sleep(delay)
    }

}
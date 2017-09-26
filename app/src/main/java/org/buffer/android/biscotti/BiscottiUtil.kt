package org.buffer.android.biscotti

import android.content.pm.ActivityInfo
import android.support.test.rule.ActivityTestRule

/**
 * General Utility methods for espresso tests
 */
object BiscottiUtil {

    fun changeOrientation(activity: ActivityTestRule<*>) {
        activity.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    }

}
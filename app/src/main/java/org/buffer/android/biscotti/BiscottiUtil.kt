package org.buffer.android.biscotti

import android.app.Instrumentation
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.SystemClock
import android.support.test.espresso.Espresso
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.intent.matcher.IntentMatchers.hasData
import android.support.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.allOf

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

    fun verifyLinkOpen(expectedUrl: String, openLink: () -> Unit) {
        Intents.init()
        val expectedIntent = allOf(hasAction(Intent.ACTION_VIEW), hasData(expectedUrl))
        intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))
        try {
            openLink()
            intended(expectedIntent)
        } finally {
            Intents.release()
        }
    }
}
package org.buffer.android.biscotti

import android.app.Instrumentation
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.SystemClock
import android.support.test.espresso.Espresso
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.intent.matcher.IntentMatchers.hasData
import android.support.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

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
        val expectedIntent = allOf(hasAction(Intent.ACTION_VIEW), hasData(expectedUrl))
        Intents.init()
        verifyExpectedIntent(expectedIntent, openLink)
    }

    fun verifyActivityLaunched(className: String, startActivity: () -> Unit) {
        val expectedIntent = IntentMatchers.hasComponent(className)
        verifyExpectedIntent(expectedIntent, startActivity)
    }

    fun verifyExpectedIntent(expectedIntent: Matcher<Intent>?, startIntent: () -> Unit) {
        Intents.init()
        intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))
        try {
            startIntent()
            intended(expectedIntent)
        } finally {
            Intents.release()
        }
    }
}
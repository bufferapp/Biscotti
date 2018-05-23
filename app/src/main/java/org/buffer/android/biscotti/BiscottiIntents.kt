package org.buffer.android.biscotti

import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.intent.matcher.IntentMatchers.hasData
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

object BiscottiIntents {

    fun verifyLinkOpen(expectedUrl: String, openLink: () -> Unit) {
        val expectedIntent = allOf(hasAction(Intent.ACTION_VIEW), hasData(expectedUrl))
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
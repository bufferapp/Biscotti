package org.buffer.android.biscotti

import android.graphics.drawable.ColorDrawable
import android.support.annotation.ColorInt
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.internal.util.Checks
import android.view.View
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher

object BiscottiMatchers {
    inline fun <reified T : TextView> withTextColor(@ColorInt color: Int): Matcher<View> {
        Checks.checkNotNull(color)
        return object : BoundedMatcher<View, T>(T::class.java) {
            public override fun matchesSafely(view: T): Boolean {
                return color == view.currentTextColor
            }

            override fun describeTo(description: Description) {
                description.appendText("No view matches with given text color")
            }
        }
    }

    fun withBackgroundColor(@ColorInt color: Int): Matcher<View> {
        Checks.checkNotNull(color)
        return object : BoundedMatcher<View, View>(View::class.java) {
            public override fun matchesSafely(view: View): Boolean {
                val background = view.background
                return background is ColorDrawable && color == background.color
            }

            override fun describeTo(description: Description) {
                description.appendText("No view matches with given background color")
            }
        }
    }
}
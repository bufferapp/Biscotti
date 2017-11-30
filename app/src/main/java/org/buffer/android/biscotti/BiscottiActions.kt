package org.buffer.android.biscotti

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.view.View
import org.hamcrest.Matcher

object BiscottiActions {

    fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController, view: View) {
                action.perform(uiController, view)
            }
        }

    }

}
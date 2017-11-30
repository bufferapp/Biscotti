package org.buffer.android.biscotti

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.*

object BiscottiShortcuts {

    fun assertAppShortcutExists(appName: String, shortcutLabel: String) {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        findAppIcon(device, appName).longClick()
        assert(device.hasObject(By.text(shortcutLabel)))
    }

    fun assertAppShortcutDoesNotExist(appName: String, shortcutLabel: String) {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        findAppIcon(device, appName).longClick()
        assert(!device.hasObject(By.text(shortcutLabel)))
    }

    private fun findAppIcon(device: UiDevice, appName: String): UiObject {
        device.pressHome()

        if (device.hasObject(By.desc("Apps"))) {
            device.findObject(By.desc("Apps")).click()
        }

        val appDrawer = UiScrollable(UiSelector().scrollable(true))
        appDrawer.scrollForward()
        appDrawer.scrollTextIntoView(appName)
        return device.findObject(UiSelector().text(appName))
    }

}
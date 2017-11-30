package org.buffer.android.biscotti

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.*

object BiscottiShortcuts {

    fun assertAppShortcutsExists(appName: String, vararg shortcutLabels: String) {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        findAppIcon(device, appName).longClick()
        shortcutLabels.forEach { assert(device.hasObject(By.text(it))) }
    }

    fun assertAppShortcutsDoNotExist(appName: String, vararg shortcutLabels: String) {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        findAppIcon(device, appName).longClick()
        shortcutLabels.forEach { assert(!device.hasObject(By.text(it))) }
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
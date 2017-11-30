package org.buffer.android.biscotti

import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.*
import org.junit.Assert

object BiscottiShortcuts {

    fun assertAppShortcutsExists(appName: String, vararg shortcutLabels: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            findAppIcon(device, appName).longClick()
            shortcutLabels.forEach {
                if (!device.hasObject(By.text(it))) {
                    Assert.fail("The sepcified shortcut was not found")
                }
            }
        }
    }

    fun assertAppShortcutsDoNotExist(appName: String, vararg shortcutLabels: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            findAppIcon(device, appName).longClick()
            shortcutLabels.forEach {
                if (device.hasObject(By.text(it))) {
                    Assert.fail("The specified shortcut was not found")
                }
            }
        }
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
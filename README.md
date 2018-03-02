# Biscotti

Now we are working on multiple projects on the Android team, we felt it necessary to begin sharing some commonly used code across projects. And now we are heavility using UI testing in these projects, we found that the same custom actions and matchers were being used in multiple places.

That's where Biscotti comes in. Biscotti is a simple collection of Custom [Actions](https://developer.android.com/reference/android/support/test/espresso/action/ViewActions.html) and [Matchers](https://developer.android.com/reference/android/support/test/espresso/matcher/ViewMatchers.html) used within [Espresso](https://developer.android.com/training/testing/espresso/index.html) tests.


The ingredients of our Biscotti currently consist of:


Matchers
--------

Coming soon :)


Actions
-------

- [withCustomConstraints()](https://github.com/bufferapp/Biscotti/blob/master/app/src/main/java/org/buffer/android/biscotti/BiscottiActions.kt#L13) - 
Perform the given view action whilst also defining a custom constraint to be applied

App Shortcuts
-------------

- [assertAppShortcutDoesNotExist()](https://github.com/bufferapp/Biscotti/blob/master/app/src/main/java/org/buffer/android/biscotti/BiscottiShortcuts.kt#L14) - Check that an app shortcut for the given app name, with the given label, does not exist

- [assertAppShortcutExists()](https://github.com/bufferapp/Biscotti/blob/master/app/src/main/java/org/buffer/android/biscotti/BiscottiShortcuts.kt#L8) - Check that an app shortcut for the given app name, with the given label, exists

Utilities
---------

- [changeOrientationToLandscape](https://github.com/bufferapp/Biscotti/blob/master/app/src/main/java/org/buffer/android/biscotti/BiscottiUtil.kt#L11) - Change the test devices orientation to landscape

- [changeOrientationToPortrait](https://github.com/bufferapp/Biscotti/blob/master/app/src/main/java/org/buffer/android/biscotti/BiscottiUtil.kt#L15) - Change the test devices orientation to portrait

- [closeSoftKeyboardWithDelay](https://github.com/bufferapp/Biscotti/blob/master/app/src/main/java/org/buffer/android/biscotti/BiscottiUtil.kt#L21) - Close the software keyboard, followed by applying a delay using the given delay value


Using Biscotti
--------------

To use Biscotti in your projects, add the following to your root build.gradle file:

    allprojects {
        repositories {
	        maven { url 'https://jitpack.io' }
        }   
    }

Followed by the dependancy in your app level build.gradle file:

    androidTestCompile 'com.github.bufferapp:Biscotti:-SNAPSHOT'
    
or
    
    androidTestImplementation 'com.github.bufferapp:Biscotti:-SNAPSHOT'

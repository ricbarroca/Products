package com.example.mobapp.utils

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

fun Int.checkIfDisplayed(): ViewInteraction =
    onView(ViewMatchers.withId(this)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

fun String.checkIfTextDisplayed(): ViewInteraction =
    onView(ViewMatchers.withText(this)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

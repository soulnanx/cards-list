package com.hivecode.hearthstonecards.ui.cardType.view


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.hivecode.hearthstonecards.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tools.fastlane.screengrab.Screengrab


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class CardTypeActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(CardTypeActivity::class.java)

    @Test
    fun test_isActivityInView() {
        Thread.sleep(700)
        Screengrab.screenshot("before_button_click");
        onView(withId(R.id.card_type_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isCardTypeInView() {
        Thread.sleep(700)
        onView(withId(R.id.cardTypeRV)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navigate_to_card_list() {
        Thread.sleep(2000)

        onView(withId(R.id.cardTypeRV))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<CardTypeAdapter.ViewHolder>(0, click()))
        Thread.sleep(2000)
        onView(withId(R.id.card_list_activity)).check(matches(isDisplayed()))
    }

}

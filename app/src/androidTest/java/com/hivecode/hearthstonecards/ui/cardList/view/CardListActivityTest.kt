package com.hivecode.hearthstonecards.ui.cardList.view

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.hivecode.data.model.CardTypeInfo
import com.hivecode.data.model.ClassCardType
import com.hivecode.data.model.RaceCardType
import com.hivecode.data.model.TypeCardType
import com.hivecode.hearthstonecards.R
import com.hivecode.hearthstonecards.ui.cardType.view.CardTypeAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class CardListActivityTest {

    lateinit var context: Context

    @Before
    fun setup(){
        context = InstrumentationRegistry.getInstrumentation()
            .targetContext;
    }

    @Test
    fun test_class_cards_were_loaded() {
        val selectedCardTypeInfo = ClassCardType()
        val selectedClassType = "Druid"

        val intent = buildIntent(selectedCardTypeInfo, selectedClassType)
        ActivityScenario.launch<CardListActivity>(intent)

        Thread.sleep(2000)
        onView(withId(R.id.card_list_activity))
            .check(matches(isDisplayed()))

        onView(withId(R.id.cardRV))
            .check(matches(atPosition(0, hasDescendant(withId(R.id.cardIV)))))
    }

    @Test
    fun test_type_cards_were_loaded() {
        val selectedCardTypeInfo = TypeCardType()
        val selectedClassType = "Hero"

        val intent = buildIntent(selectedCardTypeInfo, selectedClassType)
        ActivityScenario.launch<CardListActivity>(intent)

        Thread.sleep(2000)
        onView(withId(R.id.card_list_activity))
            .check(matches(isDisplayed()))

        onView(withId(R.id.cardRV))
            .check(matches(atPosition(0, hasDescendant(withId(R.id.cardIV)))))
    }

    @Test
    fun test_race_cards_were_loaded() {
        val selectedCardTypeInfo = RaceCardType()
        val selectedClassType = "Demon"

        val intent = buildIntent(selectedCardTypeInfo, selectedClassType)
        ActivityScenario.launch<CardListActivity>(intent)

        Thread.sleep(2000)
        onView(withId(R.id.card_list_activity))
            .check(matches(isDisplayed()))

        onView(withId(R.id.cardRV))
            .check(matches(atPosition(0, hasDescendant(withId(R.id.cardIV)))))
    }

    private fun buildIntent(
        selectedCardTypeInfo: CardTypeInfo,
        selectedClassType: String
    ) =
        CardListActivity.createIntent(
            context,
            selectedCardTypeInfo,
            selectedClassType
        )

    private fun atPosition(position: Int = 0, itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder =
                    view.findViewHolderForAdapterPosition(position)
                        ?: return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

}

package edu.gatech.seclass.crypto6300.robotTests

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import edu.gatech.seclass.crypto6300.R
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.annotation.IdRes
import org.hamcrest.TypeSafeMatcher


/*
Borrowed from https://medium.com/android-bits/espresso-robot-pattern-in-kotlin-fc820ce250f7
 */
open class BaseTestRobot {
    fun fillEditText(resId: Int, text: String): ViewInteraction =
            onView(withId(resId)).perform(ViewActions.replaceText(text), ViewActions.closeSoftKeyboard())

    fun clickButton(resId: Int): ViewInteraction = onView((withId(resId))).perform(ViewActions.click())

    fun textView(resId: Int): ViewInteraction = onView(withId(resId))

    fun editText(resId: Int): ViewInteraction = onView(withId(resId))

    fun recyclerView(resId: Int): ViewInteraction = onView(withId(resId))

    fun matchText(viewInteraction: ViewInteraction, text: String): ViewInteraction = viewInteraction
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))

    fun matchText(resId: Int, text: String): ViewInteraction = matchText(textView(resId), text)

    fun clickListItem(listRes: Int, position: Int) {
        onData(anything())
                .inAdapterView(allOf(withId(listRes)))
                .atPosition(position).perform(ViewActions.click())
    }

    fun hideKeyboard() = Espresso.closeSoftKeyboard()

    fun matchToast(text: String, decorView: Matcher<Root>): ViewInteraction {
        return onView(withText(text))
                .inRoot(decorView)
                .check(ViewAssertions.matches(isDisplayed()))
    }

    fun matchEditTextError(resId: Int, text: String): ViewInteraction {
        return editText(resId)
                .check(ViewAssertions.matches(hasErrorText(text)))
    }

    fun textViewWithParent(resId: Int): ViewInteraction {
        return onView(AllOf.allOf(instanceOf(TextView::class.java), withParent(withId(resId))))
    }

    fun matchScreen(text: String): ViewInteraction {
        return matchText(textViewWithParent(R.id.toolbar), text)
    }

    fun selectSpinner(resId: Int, selectionText: String): ViewInteraction {
        clickButton(resId)
        onData(AllOf.allOf(`is`(instanceOf(String::class.java)), `is`(selectionText))).perform(ViewActions.click())
        return onView(withId(resId))
                .check(ViewAssertions.matches(withSpinnerText(containsString(selectionText))))
    }

    fun selectRvItemByPosition(resId: Int, position: Int): ViewInteraction {
        return recyclerView(resId)
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click()))
    }

    fun fillRvItemText(resId: Int, position: Int, text: String): ViewInteraction {
        return recyclerView(resId)
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.typeText(text)))
    }

    fun checkRvItemText(resId: Int, text:String): ViewInteraction {
        return recyclerView(resId)
                .check(ViewAssertions.matches(hasDescendant(withText(text))))
    }

    fun matchDialogText(message: String) = matchText(textView(android.R.id.message), message)

    fun goBack() = Espresso.pressBack()

    fun matchRvItemCount(resId: Int, count: Int): ViewInteraction {
        return recyclerView(resId).check(ViewAssertions.matches(RecyclerViewMatchers.hasItemCount(count)))
    }

    fun getCountFromRecyclerView(@IdRes RecyclerViewId: Int): Int {
        val COUNT = intArrayOf(0)
        val matcher = object : TypeSafeMatcher<View>() {
            override protected fun matchesSafely(item: View): Boolean {
                COUNT[0] = (item as RecyclerView).adapter!!.itemCount
                return true
            }

            override fun describeTo(description: Description) {}
        }
        onView(allOf(withId(RecyclerViewId), isDisplayed())).check(ViewAssertions.matches(matcher))
        return COUNT[0]
    }
}

object RecyclerViewMatchers {
    @JvmStatic
    fun hasItemCount(itemCount: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(
                RecyclerView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("has $itemCount items")
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                return view.adapter?.itemCount == itemCount
            }
        }
    }
}
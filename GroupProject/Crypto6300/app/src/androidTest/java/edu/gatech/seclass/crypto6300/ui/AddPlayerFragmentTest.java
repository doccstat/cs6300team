package edu.gatech.seclass.crypto6300.ui;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import edu.gatech.seclass.crypto6300.R;

import org.hamcrest.core.AllOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class AddPlayerFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    private View decorView;

    @Before
    public void setUp() {
        activityScenarioRule.getScenario().onActivity(activity -> {
            decorView = activity.getWindow().getDecorView();
        });
    }

    @Test
    public void addCryptogramFragmentTest1() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("Name"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("Solution"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("Username"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("Password"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness
    }

}

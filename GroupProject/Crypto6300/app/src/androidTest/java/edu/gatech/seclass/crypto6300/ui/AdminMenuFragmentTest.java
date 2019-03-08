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
public class AdminMenuFragmentTest {


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
    public void AdminMenuFragmentTestAddPlayer() {
        onView(withId(R.id.addPlayerItem)).perform(click());

        // TODO:check the correctness
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.add_player)));
    }

    @Test
    public void AdminMenuFragmentTestAddCryptogram() {
        onView(withId(R.id.addCryptogramItem)).perform(click());

        // TODO:check the correctness
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.add_cryptogram)));
    }

    @Test
    public void AdminMenuFragmentTestViewStatisticsItem() {
        onView(withId(R.id.viewStatisticsItem)).perform(click());

        // TODO:check the correctness
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.player_statistics)));
    }

    @Test
    public void AdminMenuFragmentTestLogoutAdminItem() {
        onView(withId(R.id.logoutAdminItem)).perform(click());

        // TODO:check the correctness
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.login)));
    }

}

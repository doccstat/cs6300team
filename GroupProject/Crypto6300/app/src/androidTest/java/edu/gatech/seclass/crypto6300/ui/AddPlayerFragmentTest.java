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
    public void AddPlayerFragmentTest1() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("Name"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("Solution"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("Username"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("Password"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness

    }

    @Test
    public void AddPlayerFragmentTest2() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firsttwo"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lasttwo"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("usertwo"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passtwo"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("0"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness

        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest3() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firstthree"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lastthree"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("userthree"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passthree"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("4"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness
    }

    @Test
    public void AddPlayerFragmentTest4() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firstfour"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lastfour"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("userfour"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("p"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness of password
        onView(withId(R.id.txtPlayerPassword)).check(matches(hasErrorText(getString(R.string.error_password_length))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest5() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firstfive"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lastfive"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("u"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passfive"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness
        onView(withId(R.id.txtPlayerUsername)).check(matches(hasErrorText(getString(R.string.error_username_length))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest6() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firstsix"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("l"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("usersix"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passsix"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness
        onView(withId(R.id.txtPlayerLastName)).check(matches(hasErrorText(getString(R.string.error_lastname_length))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest7() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("f"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lastseven"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("userseven"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passseven"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness
        onView(withId(R.id.txtPlayerFirstName)).check(matches(hasErrorText(getString(R.string.error_firstname_length))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest8() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firsteight"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lasteight"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText(""));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passeight"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness

        onView(withId(R.id.txtPlayerUsername)).check(matches(hasErrorText(getString(R.string.error_username))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest9() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText(""));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lastnine"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("usernine"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passnine"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness
        onView(withId(R.id.txtPlayerFirstName)).check(matches(hasErrorText(getString(R.string.error_firstname))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest10() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firstten"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText(""));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("userten"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passten"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness
        onView(withId(R.id.txtPlayerLastName)).check(matches(hasErrorText(getString(R.string.error_lastname))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest11() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firsteleven"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lasteleven"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("usereleven"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText(""));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness
        onView(withId(R.id.txtPlayerPassword)).check(matches(hasErrorText(getString(R.string.error_password))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest12() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("#@#t4retwelve"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lasttwelve"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("usertwelve"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passtwelve"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness
        onView(withId(R.id.txtPlayerFirstName)).check(matches(hasErrorText(getString(R.string.error_letters_only))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest13() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firstthirteen"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("#$@Tvg#"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("userthirteen"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passthirteen"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness
        onView(withId(R.id.txtPlayerLastName)).check(matches(hasErrorText(getString(R.string.error_letters_only))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest14() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firstfourteen"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lastfourteen"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("#$TV4#22"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passfourteen"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness
        onView(withId(R.id.txtPlayerUsername)).check(matches(hasErrorText(getString(R.string.error_username_chars))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest15() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firstfifteen"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lastfifteen"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("userfifteen"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText(")(*&^%$#\\,/./;'][]{}_+-=>?:@\''[1432t3426$%  YB^%7i&*^%*)*(_))+{PO:?>L<LK\"!'''"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());

        // TODO:check the correctness

        onView(withId(R.id.txtPlayerPassword)).check(matches(hasErrorText(getString(R.string.error_password_chars))));
        onView(AllOf.allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(R.string.administrator_menu)));
    }

    @Test
    public void AddPlayerFragmentTest16() {
        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firstsixteen"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lastsixteen"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("usersixteen"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passsixteen"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());


        onView(withId(R.id.txtPlayerFirstName)).perform(clearText(), typeText("firstsixteenAnother"));
        onView(withId(R.id.txtPlayerLastName)).perform(clearText(), typeText("lastsixteenAnother"));
        onView(withId(R.id.txtPlayerUsername)).perform(clearText(), typeText("usersixteen"));
        onView(withId(R.id.txtPlayerPassword)).perform(clearText(), typeText("passsixteenAnother"));
        onView(withId(R.id.categorySpinner)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton)).perform(click());
        // TODO:check the correctness
    }

}

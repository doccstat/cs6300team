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
public class AddCryptogramFragmentTest {

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
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("Name"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("Solution"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: check the correctness
    }

    @Test
    public void addCryptogramFragmentTest2() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("Another name"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("Another solution"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("1"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: check the correctness
        // TODO: Should show error since attempts does't quite follow
    }

    @Test
    public void addCryptogramFragmentTest3() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("Another name"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("Another solution"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("1"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("2"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: check the correctness
        // TODO: Should show error since attempts does't quite follow
    }

    @Test
    public void addCryptogramFragmentTest4() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("Another name"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("Another solution"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("1"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: check the correctness
        // TODO: Should show error since attempts does't quite follow
    }

    @Test
    public void addCryptogramFragmentTest5() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("Another name"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("Another solution"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: check the correctness
        // TODO: Should show error since attempts does't quite follow
    }

    @Test
    public void addCryptogramFragmentTest6() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("Another name"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("Another solution"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("1"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("2"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: check the correctness
        // TODO: Should show error since attempts does't quite follow
    }

    @Test
    public void addCryptogramFragmentTest7() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("!#$@#@Some invalid name"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("some solution"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: name is invalid
    }

    @Test
    public void addCryptogramFragmentTest8() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("valid name"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("invalid solution #$%7&(8"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: solution is invalid
    }

    @Test
    public void addCryptogramFragmentTest9() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("test nine"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("snother solution nine"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());


        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("test nine"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("snother solution nine prime"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: the same name
    }

    @Test
    public void addCryptogramFragmentTest10() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("test ten"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("snother solution ten"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());


        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("test ten prime"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("snother solution ten"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: the same solution
    }

    @Test
    public void addCryptogramFragmentTest11() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText(""));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("solution eleven"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: empty name
    }

    @Test
    public void addCryptogramFragmentTest12() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("name twelve"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText(""));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: empty solution
    }

    @Test
    public void addCryptogramFragmentTest13() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("name thirteen"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("solution thirteen"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText(""));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: empty easy attempts
    }

    @Test
    public void addCryptogramFragmentTest14() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("name fourteen"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("solution fourteen"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText(""));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: empty normal attempts
    }

    @Test
    public void addCryptogramFragmentTest15() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("name fifteen"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("solution fifteen"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: empty hard attempts
    }

    @Test
    public void addCryptogramFragmentTest16() {
        onView(withId(R.id.txtCryptogramName)).perform(clearText(), typeText("name fifteen"));
        onView(withId(R.id.txtCryptogramSolution)).perform(clearText(), typeText("solution fifteen"));
        onView(withId(R.id.easy_attempts_txt)).perform(clearText(), typeText("3"));
        onView(withId(R.id.normal_attempts_txt)).perform(clearText(), typeText("2"));
        onView(withId(R.id.hard_attempts_txt)).perform(clearText(), typeText("blabla"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addCryptogramButton)).perform(click());

        // TODO: invalid hard attempts
    }

}

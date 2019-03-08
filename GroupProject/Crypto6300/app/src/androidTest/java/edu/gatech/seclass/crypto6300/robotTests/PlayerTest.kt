package edu.gatech.seclass.crypto6300.robotTests

import androidx.test.espresso.Root
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import edu.gatech.seclass.crypto6300.R
import edu.gatech.seclass.crypto6300.ui.MainActivity
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PlayerTest {
    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private lateinit var decorView: Matcher<Root>

    @Before
    fun setup() {
        decorView = withDecorView(not(activityTestRule.activity.window.decorView))
    }

    @After
    fun tearDown() {

    }

    @Test
    fun loginPlayer() {
        login {
            setUsername("player")
            setPassword("password")
            hideKeyboard()
            clickLogin()
            matchScreen(string(R.string.player_menu))
        }

        player {
            clickChooseCryptogram()
            matchScreen(string(R.string.choose_cryptogram))
        }

        player {
            // TODO try choosing a cryptogram
        }
    }

    private fun string(res: Int): String = activityTestRule.activity.getString(res)

    private fun stringFormatOnce(resId: Int, input: String) = String.format(activityTestRule.activity.getString(resId), input)
}
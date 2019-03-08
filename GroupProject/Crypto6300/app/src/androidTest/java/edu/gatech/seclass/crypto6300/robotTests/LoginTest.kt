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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginTest {
    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private lateinit var decorView: Matcher<Root>

    @Before
    fun setup() {
        decorView = withDecorView(not(activityTestRule.activity.window.decorView))
    }

    @Test
    fun loginAdminSuccess() {
        login {
            setUsername("admin")
            setPassword("admin")
            hideKeyboard()
            clickLogin()
            matchScreen(string(R.string.administrator_menu))
        }
    }

    @Test
    fun loginAdminWrongPassword() {
        login {
            setUsername("admin")
            setPassword("wrong")
            hideKeyboard()
            clickLogin()
            matchScreen(string(R.string.login))
            matchToast(string(R.string.error_wrong_username_or_password), decorView = decorView)
        }
    }

    @Test
    fun loginAdminEmptyUsername() {
        login {
            setUsername("")
            setPassword("admin")
            hideKeyboard()
            clickLogin()
            matchScreen(string(R.string.login))
            matchEditTextError(R.id.etUsername, string(R.string.error_username_required))
        }
    }

    @Test
    fun loginAdminEmptyPassword() {
        login {
            setUsername("admin")
            setPassword("")
            hideKeyboard()
            clickLogin()
            matchScreen(string(R.string.login))
            matchEditTextError(R.id.etPassword, string(R.string.error_password_required))
        }
    }


    private fun string(res: Int): String = activityTestRule.activity.getString(res)
}
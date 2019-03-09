package edu.gatech.seclass.crypto6300.robotTests

import androidx.test.espresso.Root
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import edu.gatech.seclass.crypto6300.R
import edu.gatech.seclass.crypto6300.data.AppDatabase
import edu.gatech.seclass.crypto6300.data.entities.User
import edu.gatech.seclass.crypto6300.ui.MainActivity
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@LargeTest
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LoginTest {
    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private lateinit var decorView: Matcher<Root>
    private val adminUser = User(
            firstName = "admin",
            lastName = "admin",
            username = "admin",
            password = "admin",
            category = null,
            wins = 0,
            losses = 0
    )
    private val playerUser = User(
            firstName = "Alice",
            lastName = "Eve",
            username = "alice",
            password = "password",
            category = 1,
            wins = 0,
            losses = 0
    )

    @Before
    fun setup() {
        val db = AppDatabase.getInstance(activityTestRule.activity)
        db?.clearAllTables()
        db?.userDao()?.insert(adminUser)
        db?.userDao()?.insert(playerUser)

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

    @Test
    fun loginAsPlayer_returnFailWithIncorrectUsernameOrPassword() {
        login {
            setUsername("alice")
            setPassword("wrongpassword")
            hideKeyboard()
            clickLogin()
            matchToast(string(R.string.error_wrong_username_or_password), decorView = decorView)
        }
    }

    @Test
    fun loginAsPlayer_returnSuccess() {
        setupPlayer()

        login {
            setUsername("alice")
            setPassword("password")
            hideKeyboard()
            clickLogin()
            matchScreen(string(R.string.player_menu))
        }
    }

    private fun setupPlayer() {

    }

    private fun string(res: Int): String = activityTestRule.activity.getString(res)
}
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
class AdminTest {
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
    fun addPlayer_Success() {
        loginAsAdminCorrectly()
        navToAddPlayer()

        admin {
            setFirstName("Alice")
            setLastName("Eve")
            setUsername("aliceEve")
            setPassword("password")
            selectDifficulty("Easy")
            clickAddPlayerButton()
            matchDialogText(stringFormatOnce(R.string.user_was_added, "aliceEve"))
        }
    }

    @Test
    fun addPlayer_FailWithMissingFirstname() {
        loginAsAdminCorrectly()
        navToAddPlayer()

        admin {
            setFirstName("")
            setLastName("Eve")
            setUsername("doesntmatter")
            setPassword("doesntmatter")
            selectDifficulty("Easy")
            clickAddPlayerButton()
            matchEditTextError(R.id.txtPlayerFirstName, string(R.string.error_firstname))
        }
    }

    @Test
    fun addPlayer_FailWithWrongUsernameCharacters() {
        loginAsAdminCorrectly()
        navToAddPlayer()

        admin {
            setFirstName("Alice")
            setLastName("Eve")
            setUsername("Exclamation!!")
            setPassword("doesntmatter")
            selectDifficulty("Easy")
            clickAddPlayerButton()
            matchEditTextError(R.id.txtPlayerUsername, string(R.string.error_username_chars))
        }
    }

    @Test
    fun addPlayer_FailWithLongUsername() {
        loginAsAdminCorrectly()
        navToAddPlayer()

        admin {
            setFirstName("Alice")
            setLastName("Eve")
            setUsername("123456789012345678901234567890")
            setPassword("doesntmatter")
            selectDifficulty("Normal")
            clickAddPlayerButton()
            matchEditTextError(R.id.txtPlayerUsername, string(R.string.error_username_length))
        }
    }

    @Test
    fun addPlayer_FailWithUsernameExists() {
        loginAsAdminCorrectly()
        navToAddPlayer()

        admin {
            setFirstName("something!")
            setLastName("whatever")
            setUsername("admin")
            setPassword("doesntmatter")
            selectDifficulty("Normal")
            clickAddPlayerButton()
            matchDialogText(string(R.string.username_exists))
        }
    }

    @Test
    fun addCryptogram_Success() {
        loginAsAdminCorrectly()
        navToAddCryptogram()

        admin {
            setCryptogramName("aristotle")
            setCryptogramSolution("android")
            setNumEasyAttempts(7)
            setNumNormalAttempts(5)
            setNumHardAttempts(3)
            clickAddCryptogramButton()
            matchDialogText(stringFormatOnce(R.string.cryptogram_was_added, "aristotle"))
        }
    }

    @Test
    fun addCryptogram_FailWithCryptogramExists() {
        loginAsAdminCorrectly()
        navToAddCryptogram()

        admin {
            setCryptogramName("aristotle")
            setCryptogramSolution("android")
            setNumEasyAttempts(7)
            setNumNormalAttempts(5)
            setNumHardAttempts(3)
            clickAddCryptogramButton()
            matchDialogText(string(R.string.cryptogram_exists))
        }
    }


    private fun loginAsAdminCorrectly() {
        login {
            setUsername("admin")
            setPassword("admin")
            hideKeyboard()
            clickLogin()
            matchScreen(string(R.string.administrator_menu))
        }
    }

    private fun navToAddPlayer() {
        adminMenu {
            clickMenuAddPlayer()
            matchScreen(string(R.string.add_player))
        }
    }

    private fun navToAddCryptogram() {
        adminMenu {
            clickMenuAddCryptogram()
            matchScreen(string(R.string.add_cryptogram))
        }
    }

    open fun string(res: Int): String = activityTestRule.activity.getString(res)

    open fun stringFormatOnce(resId: Int, input: String) = String.format(activityTestRule.activity.getString(resId), input)
}
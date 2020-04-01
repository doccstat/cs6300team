package edu.gatech.seclass.crypto6300.robotTests

import androidx.test.espresso.Root
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import edu.gatech.seclass.crypto6300.R
import edu.gatech.seclass.crypto6300.data.AppDatabase
import edu.gatech.seclass.crypto6300.data.entities.Attempts
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram
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
class PlayerTest {
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
    private val cryptogram = Cryptogram(
            name = "Caesar",
            solution = "android",
            difficulty = 2,
            maxAttempts = Attempts(3, 2, 1)
    )

    @Before
    fun setup() {
        val db = AppDatabase.getInstance(activityTestRule.activity)
        db?.clearAllTables()
        db?.userDao()?.insert(adminUser)
        db?.userDao()?.insert(playerUser)
        db?.cryptogramDao()?.insert(cryptogram)

        decorView = withDecorView(not(activityTestRule.activity.window.decorView))
    }

    @Test
    fun loginPlayer_navToSolveCryptogram() {
        loginAsPlayerCorrectly()
        navToChooseCryptogram()

        player {
            selectRvItemByPosition(R.id.choose_cryptogram_rv, 0)
            matchScreen(string(R.string.solve_cryptogram))
            fillSolver(R.id.solve_cryptogram_rv, "hello")
        }
    }

    @Test
    fun loginPlayer_solveCryptogramCorrectly() {
        loginAsPlayerCorrectly()
        navToChooseCryptogram()

        player {
            selectRvItemByPosition(R.id.choose_cryptogram_rv, 0)
            matchScreen(string(R.string.solve_cryptogram))

            fillSolver(R.id.solve_cryptogram_rv, "android")
            clickSubmitSolution()
            matchScreen(string(R.string.game_won))

            matchText(R.id.tvLostW, "1")
            matchText(R.id.tvLostL, "0")

            clickButton(R.id.returnToMenu)
            matchScreen(string(R.string.player_menu))

            clickMenuViewPlayerStats()
            matchScreen(string(R.string.player_statistics))

            checkRvItemText(R.id.playerList, playerUser.username)
            matchText(R.id.row_wins, "1")
            matchText(R.id.row_losses, "0")
        }
    }

    @Test
    fun loginPlayer_solveCryptogramFail() {
        loginAsPlayerCorrectly()
        navToChooseCryptogram()

        player {
            selectRvItemByPosition(R.id.choose_cryptogram_rv, 0)
            matchScreen(string(R.string.solve_cryptogram))

            // Max 3 attempts until failure for this cryptogram
            fillSolver(R.id.solve_cryptogram_rv, "wronganswer")
            clickSubmitSolution()
            matchScreen(string(R.string.solve_cryptogram))

            fillSolver(R.id.solve_cryptogram_rv, "wronganswer2")
            clickSubmitSolution()
            matchScreen(string(R.string.solve_cryptogram))

            fillSolver(R.id.solve_cryptogram_rv, "wronganswer3")
            clickSubmitSolution()
            matchScreen(string(R.string.game_over))

            matchText(R.id.tvWon, "0")
            matchText(R.id.tvLost, "1")

            clickButton(R.id.returnToMenu)
            matchScreen(string(R.string.player_menu))

            clickMenuViewPlayerStats()
            matchScreen(string(R.string.player_statistics))

            checkRvItemText(R.id.playerList, playerUser.username)
            matchText(R.id.row_wins, "0")
            matchText(R.id.row_losses, "1")
        }
    }

    @Test
    fun testAttemptStartedAndChooseScreenIsInProgress() {
        loginAsPlayerCorrectly()
        navToChooseCryptogram()

        player {
            selectRvItemByPosition(R.id.choose_cryptogram_rv, 0)
            matchScreen(string(R.string.solve_cryptogram))
            goBack()
            matchScreen(string(R.string.choose_cryptogram))
            matchRvItemCount(R.id.choose_cryptogram_rv, 1)
            checkRvItemText(R.id.choose_cryptogram_rv, string(R.string.in_progress))

            selectRvItemByPosition(R.id.choose_cryptogram_rv, 0)
            matchScreen(string(R.string.solve_cryptogram))
        }
    }


    private fun loginAsPlayerCorrectly() {
        login {
            setUsername("alice")
            setPassword("password")
            hideKeyboard()
            clickLogin()
            matchScreen(string(R.string.player_menu))
        }
    }

    private fun navToChooseCryptogram() {
        player {
            clickChooseCryptogram()
            matchScreen(string(R.string.choose_cryptogram))
        }
    }

    private fun navToSolveCryptogram() {
        player {
            selectRvItemByPosition(R.id.choose_cryptogram_rv, 0)
            matchScreen(string(R.string.solve_cryptogram))
        }
    }


    private fun string(res: Int): String = activityTestRule.activity.getString(res)

    private fun stringFormatOnce(resId: Int, input: String) = String.format(activityTestRule.activity.getString(resId), input)
}
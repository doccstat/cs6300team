package edu.gatech.seclass.crypto6300.robotTests

import androidx.test.espresso.ViewInteraction
import edu.gatech.seclass.crypto6300.R

fun player(func: PlayerRobot.() -> Unit) = PlayerRobot().apply { func() }

class PlayerRobot : BaseTestRobot() {

    // menu UI
    fun clickChooseCryptogram() = clickButton(R.id.chooseCryptogramItem)

    fun clickMenuViewPlayerStats() = clickButton(R.id.viewStatisticsItem)

    fun clickMenuLogout() = clickButton(R.id.logoutPlayerItem)

    // choose cryptogram


    // solve cryptogram
    fun fillSolver(resId: Int, text: String): ViewInteraction {
        val split = ArrayList(text.split(""))
        split.removeAt(0)  // pop first element because it's empty

        val itemCount = getCountFromRecyclerView(resId)

        split.forEachIndexed { index, s ->
            if (index < itemCount) {
                fillRvItemText(resId = resId, position = index, text = s)
            }
        }

        return recyclerView(resId)
    }

    fun clickSubmitSolution() = clickButton(R.id.submitSubmission)
}
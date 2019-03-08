package edu.gatech.seclass.crypto6300.robotTests

import edu.gatech.seclass.crypto6300.R

fun player(func: PlayerRobot.() -> Unit) = PlayerRobot().apply { func() }

class PlayerRobot : BaseTestRobot() {

    // menu UI
    fun clickChooseCryptogram() = clickButton(R.id.chooseCryptogramItem)

    fun clickMenuViewPlayerStats() = clickButton(R.id.viewStatisticsItem)

    fun clickMenuLogout() = clickButton(R.id.logoutPlayerItem)

    // choose cryptogram


    // solve cryptogram


    fun clickSubmitSolution() = clickButton(R.id.submitSubmission)
}
package edu.gatech.seclass.crypto6300.robotTests

import edu.gatech.seclass.crypto6300.R

fun admin(func: AdminRobot.() -> Unit) = AdminRobot().apply { func() }

fun adminMenu(func: AdminRobot.() -> Unit) = AdminRobot().apply { func() }

class AdminRobot : BaseTestRobot() {

    // menu UI
    fun clickMenuAddPlayer() = clickButton(R.id.addPlayerItem)

    fun clickMenuAddCryptogram() = clickButton(R.id.addCryptogramItem)

    fun clickMenuViewPlayerStats() = clickButton(R.id.viewPlayerStatisticsItem)

    fun clickMenuLogout() = clickButton(R.id.logoutAdminItem)


    // add player
    fun setFirstName(firstname: String) = fillEditText(R.id.txtPlayerFirstName, firstname)

    fun setLastName(lastname: String) = fillEditText(R.id.txtPlayerLastName, lastname)

    fun setUsername(username: String) = fillEditText(R.id.txtPlayerUsername, username)

    fun setPassword(password: String) = fillEditText(R.id.txtPlayerPassword, password)

    fun selectDifficulty(difficulty: String) = selectSpinner(R.id.categorySpinner, difficulty)

    fun clickAddPlayerButton() = clickButton(R.id.addPlayerButton)


    // add cryptogram
    fun setCryptogramName(name: String) = fillEditText(R.id.txtCryptogramName, name)

    fun setCryptogramSolution(solution: String) = fillEditText(R.id.txtCryptogramSolution, solution)

    fun setNumEasyAttempts(num: Int) = fillEditText(R.id.easy_attempts_txt, num.toString())

    fun setNumNormalAttempts(num: Int) = fillEditText(R.id.normal_attempts_txt, num.toString())

    fun setNumHardAttempts(num: Int) = fillEditText(R.id.hard_attempts_txt, num.toString())

    fun clickAddCryptogramButton() = clickButton(R.id.addCryptogramButton)


    // generic
    fun matchDialogText(message: String) = matchText(textView(android.R.id.message), message)
}

package edu.gatech.seclass.crypto6300.robotTests

import edu.gatech.seclass.crypto6300.R

/*
Borrowed from https://github.com/faruktoptas/espresso-robot-pattern-sample/blob/master/app/src/androidTest/java/com/example/espressorobot/LoginRobot.kt
 */

fun login(func: LoginRobot.() -> Unit) = LoginRobot().apply { func() }

class LoginRobot : BaseTestRobot() {
    fun setUsername(username: String) = fillEditText(R.id.etUsername, username)

    fun setPassword(password: String) = fillEditText(R.id.etPassword, password)

    fun clickLogin() = clickButton(R.id.btnLogin)

    fun matchErrorText(err: String) = matchText(editText(android.R.id.message), err)
}
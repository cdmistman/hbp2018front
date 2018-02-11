package com.coolkids.todo.todoapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText

/**
 * Log in pages
 */
class LogInActivity : AppCompatActivity() {
    // The text fields
    private var usernameLogInField : EditText? = null
    private var passwordLogInField : EditText? = null

    // The buttons
    private var loginButton : Button? = null
    private var registerButton : Button? = null

    // Server handler
    private var serverHandler : ServerHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameLogInField = findViewById<EditText>(R.id.username_login_field)
        passwordLogInField = findViewById<EditText>(R.id.password_login_field)

        loginButton = findViewById<Button>(R.id.log_in_button)
        registerButton = findViewById<Button>(R.id.register_button)
    }

    fun setServerHandler(handler: ServerHandler) {
        this.serverHandler = handler
    }

    fun handleSignIn(v: View) {
        var usernameEntered = usernameLogInField!!.text.toString()
        var passwordEntered = passwordLogInField!!.text.toString()

        if (usernameEntered.length == 0 || passwordEntered.length == 0)
        {
            //var loginError = findViewById<TextView>(R.id.invalid_creds_error)
            //loginError.visibility = View.VISIBLE
            TODO()
        }
        else
        {
            TODO()
        }
    }

    fun goToRegister(v: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}
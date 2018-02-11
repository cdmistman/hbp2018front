package com.coolkids.todo.todoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


/**
 * Log in pages
 */
class LogInActivity : AppCompatActivity() {
    // The text fields
    private var usernameLogInField: EditText? = null
    private var passwordLogInField: EditText? = null

    // The buttons
    private var loginButton: Button? = null
    private var registerButton: Button? = null

    // Server handler
    // private var serverHandler : ServerHandler? = null

    // SharedPreferences stuff
    private val USER_ACC_INFO = "UserAccount"
    var editor: SharedPreferences.Editor? = null
    var reader: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Not sure how to init the server handler?
        // this.serverHandler = ServerHandler.init

        usernameLogInField = findViewById<EditText>(R.id.username_login_field)
        passwordLogInField = findViewById<EditText>(R.id.password_login_field)

        loginButton = findViewById<Button>(R.id.log_in_button)
        registerButton = findViewById<Button>(R.id.register_button)

        // init the Shared Preferences stuff
        editor = getSharedPreferences(USER_ACC_INFO, Context.MODE_PRIVATE).edit()
        reader = getSharedPreferences(USER_ACC_INFO, Context.MODE_PRIVATE)

        // Check to see if the user is already signed in
        val tempFName: String? = reader!!.getString("first_name", "")
        val tempLName: String? = reader!!.getString("last_name", "")
        val tempUName: String? = reader!!.getString("user_name", "")
        val tempEmail: String? = reader!!.getString("user_email", "")
        val tempPwd: String? = reader!!.getString("password", "")
        // this.serverHandler.validateCredentials(tempUName,
        //          tempPwd, { u -> alreadySignedIn(u) },
        //          {  })
    }

    fun alreadySignedIn(v: View) {
        val skipToMainActivity = Intent(this@LogInActivity, MainActivity::class.java)
        this@LogInActivity.startActivity(skipToMainActivity)
    }

    fun handleSignIn(v: View) {
        var usernameEntered = usernameLogInField!!.text.toString()
        var passwordEntered = passwordLogInField!!.text.toString()

        //this.serverHandler
        //            .validateCredentials(usernameEntered, passwordEntered,
        //                    { u -> validUser(u, this.editor) },
        //                    {  })
    }

    fun validUser(u: User, e: SharedPreferences.Editor) {
        e.putString("first_name", u.fname)
        e.putString("last_name", u.lname)
        e.putString("user_name", u.uname)
        e.putString("password", u.pwd)
        e.apply()

        val goToJoinCreateActivity = Intent(this@LogInActivity, JoinCreateActivity::class.java)
        this@LogInActivity.startActivity(goToJoinCreateActivity)
    }

    fun invalidUser(v: View) {
        val invalidCreds = findViewById<TextView>(R.id.invalid_creds_error)
        invalidCreds.visibility = View.VISIBLE
    }

    fun goToRegister(v: View) {
        val goToSignUpActivity = Intent(this@LogInActivity, SignUpActivity::class.java)
        this@LogInActivity.startActivity(goToSignUpActivity)
    }
}
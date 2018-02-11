package com.coolkids.todo.getTogether

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.sign


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
    lateinit var reader: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        reader = this.getPreferences(Context.MODE_PRIVATE)

        // Not sure how to init the server handler?
        // this.serverHandler = ServerHandler.init
        if(reader.contains("user_name") && reader.contains("password")){
            signIn(reader.getString("user_name",null), reader.getString("password",null))
        }

        usernameLogInField = findViewById<EditText>(R.id.username_login_field)
        passwordLogInField = findViewById<EditText>(R.id.password_login_field)

        loginButton = findViewById<Button>(R.id.log_in_button)
        registerButton = findViewById<Button>(R.id.register_button)
    }

    fun alreadySignedIn(v: View) {
        val skipToMainActivity = Intent(this@LogInActivity, MainActivity::class.java)
        this@LogInActivity.startActivity(skipToMainActivity)
    }

    fun handleSignIn(v: View) {
        var usernameEntered = usernameLogInField!!.text.toString()
        var passwordEntered = passwordLogInField!!.text.toString()

        signIn(usernameEntered,passwordEntered)
    }

    fun signIn(usernameEntered:String, passwordEntered:String){

        ServerHandler.serverHandler
                    .validateCredentials(usernameEntered, passwordEntered,
                            { u ->
                                with(reader.edit()){
                                    putString("user_name", usernameEntered)
                                    putString("password", passwordEntered)
                                    commit()
                                }
                                goToMain()
                            },
                            { e-> invalidUser() })
    }


    fun goToMain(){
        val intent = Intent(this@LogInActivity, MainActivity::class.java)
        startActivity(intent)
    }


    fun validUser(u: User, e: SharedPreferences.Editor) {
        e.putString("first_name", u.fname)
        e.putString("last_name", u.lname)
        e.putString("user_name", u.uname)
        e.putString("password", u.pwd)
        e.apply()

        val goToJoinCreateActivity = Intent(this@LogInActivity, JoinCreateActivity::class.java)
        ServerHandler.serverHandler.saveCredentials(u.uname, u.pwd)
        this@LogInActivity.startActivity(goToJoinCreateActivity)
    }

    fun invalidUser() {
        val invalidCreds = findViewById<TextView>(R.id.invalid_creds_error)
        invalidCreds.visibility = View.VISIBLE
    }

    fun goToRegister(v: View) {
        val goToSignUpActivity = Intent(this@LogInActivity, SignUpActivity::class.java)
        this@LogInActivity.startActivity(goToSignUpActivity)
    }
}
package com.coolkids.todo.todoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.VolleyError

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SignUpActivity : AppCompatActivity() {
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    // Register button
    private val confirmButton = findViewById<Button>(R.id.confirm_button)

    // User's information fields
    private var firstNameField: EditText? = null
    private var lastNameField: EditText? = null
    private var userNameField: EditText? = null
    private var emailField: EditText? = null
    private var passwordField: EditText? = null
    private var passwordConfirmField: EditText? = null

    // error text
    private var errorText: TextView? = null

    // server handler for communicating with the server
    private var serverHandler: ServerHandler = ServerHandler.serverHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)

        firstNameField = findViewById<EditText>(R.id.first_name_field)
        lastNameField = findViewById<EditText>(R.id.last_name_field)
        userNameField = findViewById<EditText>(R.id.username_field)
        emailField = findViewById<EditText>(R.id.email_field)
        passwordField = findViewById<EditText>(R.id.password_field)
        passwordConfirmField = findViewById<EditText>(R.id.confirm_password_field)

        errorText = findViewById<TextView>(R.id.error_text)

    }

    fun confirmCredentials(v: View) {
        if (checkCredentials())
            createUser()
        else
            errorText!!.visibility = View.VISIBLE
    }

    fun createUser() {
        serverHandler?.newUser(firstNameField!!.text.toString(),
                lastNameField!!.text.toString(),
                userNameField!!.text.toString(),
                emailField!!.text.toString(),
                passwordField!!.text.toString(),
                { it: AppUser ->
                    val editor : SharedPreferences.Editor = getSharedPreferences("UserAccount", Context.MODE_PRIVATE).edit()
                    editor.putString("first_name", it.fname)
                    editor.putString("last_name", it.lname)
                    editor.putString("user_name", it.uname)
                    editor.putString("user_email", it.email)
                    editor.apply()
                },
                { it: VolleyError? ->
                    TODO()
                })
        val intent = Intent(this@SignUpActivity, JoinCreateActivity::class.java)
        this@SignUpActivity.startActivity(intent)
    }

    fun checkCredentials(): Boolean {
        val firstNameValid = true
        val lastNameValid = true
        val userNameValid = true
        val userNameAvailable = true
        val emailValid = true
        val passwordValid = true
        val passwordConfirmed = passwordField == passwordConfirmField
        return firstNameValid &&
                lastNameValid &&
                userNameValid &&
                userNameAvailable &&
                emailValid &&
                passwordValid &&
                passwordConfirmed
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

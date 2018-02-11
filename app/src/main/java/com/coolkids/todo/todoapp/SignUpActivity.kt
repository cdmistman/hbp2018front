package com.coolkids.todo.todoapp

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

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
    private val firstNameField = findViewById<EditText>(R.id.first_name_field)
    private val lastNameField = findViewById<EditText>(R.id.last_name_field)
    private val userNameField = findViewById<EditText>(R.id.username_field)
    private val emailField = findViewById<EditText>(R.id.email_field)
    private val passwordField = findViewById<EditText>(R.id.password_field)
    private val passwordConfirmField = findViewById<EditText>(R.id.confirm_password_field)

    // error text
    private val errorText = findViewById<TextView>(R.id.error_text)

    // server handler for communicating with the server
    var serverHandler : ServerHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)
    }

    fun confirmCredentials(v: View) {
        if (checkCredentials())
            createUser()
        else
            errorText.visibility = View.VISIBLE
    }

    fun createUser() {
        try {
            serverHandler?.newUser(firstNameField.text.toString(),
                    lastNameField.text.toString(), userNameField.text.toString(),
                    emailField.text.toString(), passwordField.text.toString(),
                    { TODO() })
        } catch (e: NullPointerException) {
            throw NullPointerException("You didn't initialize the serverHandler!")
        }
        val intent = Intent(this, JoinCreateActivity::class.java)
        startActivity(intent)
    }

    fun checkCredentials(): Boolean {
        var firstNameValid = true
        var lastNameValid = true
        var userNameValid = true
        var userNameAvailable = true
        var emailValid = true
        var passwordValid = true
        var passwordConfirmed = (passwordField.equals(passwordConfirmField))
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

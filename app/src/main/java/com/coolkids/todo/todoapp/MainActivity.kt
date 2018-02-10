package com.coolkids.todo.todoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    // If the app already contains the user's credentials,
    //  then carries on with the MainActivity. If the user
    //  hasn't signed into the app yet, then goes to
    //  the LoginActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (hasCredentials())
            goToLoginActivity()
        else
            waywardSon()
    }

    // Checks to see if the user has signed into the app
    //  before
    fun hasCredentials(): Boolean {
        TODO()
    }

    // Goes to the LoginActivity
    fun goToLoginActivity() {
        TODO()
    }

    // Carries on with the MainActivity
    fun waywardSon() {
        TODO()
    }

    // Hamburger menu
    fun hamburgerMenu() {
        TODO()
    }

    // Floating button for the user to join or create a
    //  new event
    fun floatingButton() {
        TODO()
    }

    // Template for each Event the user is a part of
    fun listItem() {
        TODO()
    }

    // Goes to the list item's activity
    fun goToListItemActivity(listItem: Object) {
        TODO()
    }
}
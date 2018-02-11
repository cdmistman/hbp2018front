package com.coolkids.todo.todoapp

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by colton on 2/10/18.
 */
class AppUser {
    var uname = ""
    var fname = ""
    var lname = ""
    var email = ""

    constructor(username: String, firstName: String, lastName: String, userEmail: String){
        this.uname = username
        this.fname = firstName
        this.lname = lastName
        this.email = userEmail
    }

    constructor (userJSON: JSONObject) {
        val username: String
        val firstName: String
        val lastName: String
        val userEmail: String
        try {
            username = userJSON.getString("uname")
            firstName = userJSON.getString("firstName")
            lastName = userJSON.getString("lastName")
            userEmail = userJSON.getString("email")
        } catch (e: JSONException) {
            throw IllegalArgumentException("The JSONObject received from" + " the server doesn't have the right names lol")
        }

        this.uname = username
        this.fname = firstName
        this.lname = lastName
        this.email = userEmail
    }
}
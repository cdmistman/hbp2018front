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

    constructor(username: String, firstName: String, lastName: String){
        this.uname = username
        this.fname = firstName
        this.lname = lastName
    }

    constructor (userJSON: JSONObject) {
        val username: String
        val firstName: String
        val lastName: String
        try {
            username = userJSON.getString("username")
            firstName = userJSON.getString("first name")
            lastName = userJSON.getString("last name")
        } catch (e: JSONException) {
            throw IllegalArgumentException("The JSONObject received from" + " the server doesn't have the right names lol")
        }

        this.uname = username
        this.fname = firstName
        this.lname = lastName
    }
}
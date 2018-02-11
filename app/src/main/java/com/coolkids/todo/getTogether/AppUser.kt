package com.coolkids.todo.getTogether

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by colton on 2/10/18.
 */
class AppUser {
    var uname = ""
    var fname = ""
    var lname = ""

    constructor(username: String, firstName: String, lastName: String, userEmail: String){
        this.uname = username
        this.fname = firstName
        this.lname = lastName
    }

    constructor (userJSON: JSONObject) {
        val username: String
        val firstName: String
        val lastName: String
        try {
            username = userJSON.getString("uname")
            firstName = userJSON.getString("firstName")
            lastName = userJSON.getString("lastName")
        } catch (e: JSONException) {
            Log.d("JSON Deserialize", userJSON.toString())
            throw IllegalArgumentException("The JSONObject received from" + " the server doesn't have the right names lol")
        }

        this.uname = username
        this.fname = firstName
        this.lname = lastName
    }
}
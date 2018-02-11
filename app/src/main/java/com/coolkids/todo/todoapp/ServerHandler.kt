package com.coolkids.todo.todoapp


import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import org.json.JSONArray

import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap
import java.util.function.Consumer

/**
 * Handles the interactions with the server
 */

class ServerHandler private constructor() {

    internal var server: String = "http://localhost"
    internal var port: Int = 80
    internal var uname: String = "joe9"
    internal var pass: String = "secret"

    fun validateCredentials(username: String, password: String): Boolean {
        return true
    }

    fun addCredentials(m: MutableMap<String, String>): Map<String, String> {
        m["uname"] = uname
        m["password"] = pass
        return m
    }

    fun makeObjRequest(url: String, params: Map<String, String>,
                       callback: (JSONObject) -> Unit, errCallback: Response.ErrorListener?) {
        val request = JsonObjectRequest(
                Request.Method.POST, server + url, JSONObject(params),
                object: Response.Listener<JSONObject>{
                    override fun onResponse(response: JSONObject) {
                        callback(response);
                    }
                }, errCallback);
    }



    fun newUser(firstName: String, lastName: String, username: String,
                email: String, password: String,
                callback: (AppUser) -> Unit, errCallback: Response.ErrorListener?) {
        val req = HashMap<String, String>()
        req["firstName"] = firstName
        req["lastName"] = lastName
        req["uname"] = username
        req["email"] = email
        req["password"] = password

        val jsArrToEvents = {jsobj: JSONObject ->
            callback(AppUser(jsobj))
        }

        makeObjRequest(
                "/users", req,
                jsArrToEvents, errCallback)
    }

    fun fetchEvents(callback:(ArrayList<PlannedEvent>) -> Unit) {
        val jsArrToEvents = {jsobj: JSONObject ->
            val jsarr = jsobj.getJSONArray("events");
            var ret:ArrayList<PlannedEvent> = ArrayList<PlannedEvent>()
            for(i in 0..jsarr.length()-1){
                ret.add(PlannedEvent(jsarr.getJSONObject(i)))
            }
            callback(ret)
        }

        makeObjRequest("/events/all", addCredentials(HashMap<String,String>()),jsArrToEvents,null);
    }

    companion object {

        private var singleton: ServerHandler = ServerHandler()

        val serverHandler: ServerHandler
            get() {
                return singleton
            }
    }
}


// httpserver thing .get(address, params)
// post requests
// put requests
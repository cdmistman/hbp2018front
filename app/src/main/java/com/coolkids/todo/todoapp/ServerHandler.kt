package com.coolkids.todo.todoapp


import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.*
import org.json.JSONArray

import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap
import java.util.function.Consumer


/**
 * Handles the interactions with the server
 */

class ServerHandler private constructor() {
    var cache: Cache = NoCache()
    var network: Network = BasicNetwork(HurlStack())
    internal var queue: RequestQueue = RequestQueue(cache, network)
    internal var server: String = "https://hbptodoapp.herokuapp.com"
    internal var port: Int = 80
    internal var uname: String = "joe9"
    internal var pass: String = "secret"

    init {
        queue.start()
    }

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
        Log.d("Fetchevents","B")
        val request = JsonObjectRequest(
                Request.Method.POST, server + url, JSONObject(params),
                object: Response.Listener<JSONObject>{
                    override fun onResponse(response: JSONObject) {
                        Log.d("Fetchevents","C")
                        callback(response)
                    }
                },
                object:Response.ErrorListener{
                    override fun onErrorResponse(error: VolleyError?) {
                        Log.e("Fetchevents",error.toString())
                    }
                });

        queue.add(request)

        Log.d("Fetchevents","D")

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
        Log.d("Fetchevents","A")
        val jsArrToEvents = { jsobj: JSONObject ->
            val jsarr = jsobj.getJSONArray("events");
            val f : String = jsarr.toString();
            Log.d("Fetchevents",f)
            var ret: ArrayList<PlannedEvent> = ArrayList<PlannedEvent>()
            for (i in 0..jsarr.length() - 1) {
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
package com.coolkids.todo.todoapp;




import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Handles the interactions with the server
 */

public class ServerHandler {

    private static ServerHandler singleton;

    public static ServerHandler getServerHandler(){
        if(singleton == null){
            singleton = new ServerHandler();
            singleton.server = "http://localhost";
        }
        return singleton;
    }
    private ServerHandler(){}

    String server;
    int port;
    String uname;
    String pass;

    public boolean validateCredentials(String username, String password) {
        return true;
    }

    public Map<String,String> addCredentials(Map<String,String> m){
        m.put("uname",uname);
        m.put("password",pass);
        return m;
    }

    public void makeObjRequest(String url, Map<String,String> params,
                               Response.Listener<JSONObject> callback, Response.ErrorListener errCallback){

    }


    public void newUser(String firstName, String lastName, String username,
                        String email, String password,
                        final Response.Listener<JSONObject> callback, Response.ErrorListener errCallback) {
        HashMap<String,String> req = new HashMap<>();
        req.put("firstName",firstName);
        req.put("lastName",lastName);
        req.put("uname", username);
        req.put("email", email);
        req.put("password", password);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, server + "/users", new JSONObject(req), callback, errCallback);

    public ArrayList<PlannedEvent> fetchEvents() {
        //TODO
        return null;
    }
}


// httpserver thing .get(address, params)
// post requests
// put requests
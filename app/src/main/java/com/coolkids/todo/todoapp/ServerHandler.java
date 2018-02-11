package com.coolkids.todo.todoapp;


import java.util.ArrayList;

/**
 * Handles the interactions with the server
 */

public class ServerHandler {
    String server;
    int port;

    public boolean validateCredentials(String username, String password) {
        return true;
    }

    public void newUser(String firstName, String lastName, String username,
                        String email, String password) {
        //TODO
    }

    public ArrayList<PlannedEvent> fetchEvents() {
        //TODO
        return null;
    }
}


// httpserver thing .get(address, params)
// post requests
// put requests
package com.coolkids.todo.todoapp

/**
 * Created by colton on 2/10/18.
 */
class User(username: String, firstName: String, lastName: String, password: String, currEvents: List<PlannedEvent>) {
    var uname = username
    var pwd = password
    var fname = firstName
    var lname = lastName
    var events = currEvents

    fun verifyCredentials(serverHandler: ServerHandler): Boolean {
        return true
    }
}
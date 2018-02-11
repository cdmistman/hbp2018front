package com.coolkids.todo.todoapp

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by colton on 2/10/18.
 */
class ToDoTask {
    var id = 0
    var name = ""
    var description = ""
    var isAssigned = false
    var assignedTo : AppUser? = null

    constructor(taskID: Int, taskName: String, taskDescription: String, taskAssigned: Boolean, taskAssignedTo: AppUser){
        id = taskID
        name=taskName
        description=taskDescription
        isAssigned=taskAssigned
        assignedTo = taskAssignedTo
    }

    constructor(jsobj: JSONObject) {
        try {
            id = jsobj.getInt("id")
            name = jsobj.getString("name")
            description = jsobj.getString("description")
            isAssigned = jsobj.getBoolean("isAssigned")
            if (isAssigned) {
                assignedTo = AppUser(jsobj.getJSONObject("assignedTo"))
            }
        } catch (e: JSONException) {
            throw IllegalArgumentException("The JSONObject received from" + " the server doesn't have the right names lol")
        }
    }
}
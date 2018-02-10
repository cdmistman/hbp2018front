package com.coolkids.todo.todoapp

/**
 * Created by colton on 2/10/18.
 */
class ToDoTask(taskID: Int, taskName: String, taskDescription: String, taskAssigned: Boolean) {
    var id = taskID
    var name = taskName
    var description = taskDescription
    var isAssigned = taskAssigned
    var assignedTo : AppUser? = null

    constructor(taskID: Int, taskName: String, taskDescription: String, taskAssigned: Boolean, taskAssignedTo: AppUser) : this(taskID, taskName, taskDescription, taskAssigned)
    {
        assignedTo = taskAssignedTo
    }
}
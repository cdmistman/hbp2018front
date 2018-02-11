package com.coolkids.todo.getTogether

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.VolleyError

class CreateTaskActivity : AppCompatActivity() {
    private val serverHandler: ServerHandler = ServerHandler.serverHandler

    private var taskTitle: EditText? = null
    private var taskDescription: EditText? = null
    private var eventTitle: TextView? = null

    var parentEventTitle: String? = null
    var parentEventId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parentEventTitle = intent.getStringExtra("PARENT_EVENT")
        parentEventId = intent.getStringExtra("PARENT_ID")

        setContentView(R.layout.activity_createtask)

        taskTitle = findViewById(R.id.create_task_title)
        taskDescription = findViewById(R.id.create_task_description)
        eventTitle = findViewById(R.id.create_task_event_name)
        eventTitle!!.text = parentEventTitle
    }

    fun createTask(v: View) {
        serverHandler.createTask(parentEventId!!,
                taskTitle!!.text.toString(), taskDescription!!.text.toString(),
                { t: ToDoTask ->
                    TODO()
                    //val goToEventActivity = Intent(this@CreateTaskActivity, EventActivity::class.java)
                    //this@CreateTaskActivity.startActivity(goToEventActivity)
                },
                { e: VolleyError? ->
                    val cantCreateTaskError = findViewById<TextView>(R.id.cant_create_event_error)
                    cantCreateTaskError.visibility = View.VISIBLE
                })
    }
}

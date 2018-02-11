package com.coolkids.todo.getTogether

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import com.android.volley.VolleyError

class EventActivity : AppCompatActivity() {
    //stuff for the recycler view
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<TaskAdapter.ViewHolder>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    private var serverHandler = ServerHandler.serverHandler

    // Event's ID
    private var eventID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        eventID = intent.getStringExtra("EVENT_ID")

        setContentView(R.layout.activity_event)
        mRecyclerView = findViewById(R.id.task_recycler_view)

        // get layout manager
        mLayoutManager = LinearLayoutManager(this)
        this.serverHandler = ServerHandler.serverHandler

        fetchTasks()
    }

    fun setupRecyclerView(tasks: ArrayList<ToDoTask>) {
        // set up the adapter
        mAdapter = TaskAdapter(tasks)

        // finishing setting up the RecyclerView
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mAdapter

        Log.d("TasksList", tasks.toString())

        var noTasksText = findViewById<TextView>(R.id.no_tasks_text)
        if (tasks.isEmpty()) {
            noTasksText.visibility = TextView.VISIBLE
        } else {
            noTasksText.visibility = TextView.GONE
        }
    }

    fun opencreatetask(v: View){
        val intent = Intent(this, CreateTaskActivity::class.java)
        intent.putExtra("PARENT_ID",eventID)
        startActivity(intent)
    }

    fun fetchTasks() {
        this.serverHandler!!.fetchOneEvent(eventID!!,
                { event: PlannedEvent ->
                    setupRecyclerView(event.tasks)
                },
                { err: VolleyError? ->
                    setupRecyclerView(ArrayList())
                })
    }
}

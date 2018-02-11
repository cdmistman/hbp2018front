package com.coolkids.todo.todoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MainActivity : AppCompatActivity() {
    // stuff for the recycler view
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>? = null
    private var mLayoutManager : RecyclerView.LayoutManager? = null

    private var serverHandler : ServerHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        // get layout manager
        mLayoutManager = LinearLayoutManager(this)

        // set up the adapter
        mAdapter = TodoAdapter(fetchEvents())

        // finish setting up the RecyclerView
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mAdapter
    }

    fun setServerHandler(handler: ServerHandler) {
        this.serverHandler = handler
    }

    fun fetchEvents() : ArrayList<PlannedEvent> {
        return this.serverHandler!!.fetchEvents()
    }
}

package com.coolkids.todo.getTogether

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView

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
        this.serverHandler = ServerHandler.serverHandler

        fetchEvents()
    }

    fun setServerHandler(handler: ServerHandler) {
        this.serverHandler = handler
    }

    fun setupRecyclerView(events: ArrayList<PlannedEvent>) {
        // set up the adapter
        mAdapter = TodoAdapter(events)

        // finish setting up the RecyclerView
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mAdapter

        Log.d("EventsList", events.toString())
        var noEventsText = findViewById<TextView>(R.id.title_text)
        if(events.isEmpty()){
            noEventsText.visibility = TextView.VISIBLE
        } else{
            noEventsText.visibility = TextView.GONE
        }
    }

    fun fetchEvents() {
        this.serverHandler!!.fetchEvents({events -> setupRecyclerView(events);})
    }
}

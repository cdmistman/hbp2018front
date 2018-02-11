package com.coolkids.todo.todoapp

import android.support.v7.widget.RecyclerView
import android.util.EventLog
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_element.view.*


class RecyclerAdapter(private val events: ArrayList<PlannedEvent>) : RecyclerView.Adapter<RecyclerAdapter.EventHolder>() {
    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.EventHolder, position: Int) {
        val itemEvent = events[position]
        holder.bindEvent(itemEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.EventHolder{
        val inflatedView = parent.inflate(R.layout.list_element, false)
        return EventHolder(inflatedView) //To change body of created functions use File | Settings | File Templates.
    }


    class EventHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var event: PlannedEvent? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Log.d("RecyclerView", "Click")
        }

        fun bindEvent(event: PlannedEvent){
            this.event = event
            view.listItemtitle.text = event.name
            view.listItemDesc.text = event.description
        }

        companion object {
            private val PHOTO_KEY = "PHOTO"
        }
    }
}
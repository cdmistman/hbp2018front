package com.coolkids.todo.todoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.android.volley.VolleyError

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class CreateEventActivity : AppCompatActivity() {
    private var eventNameField: EditText? = null
    private var eventDescriptionField: EditText? = null
    private var eventDateField: EditText? = null
    private var eventTimeField: EditText? = null
    private var eventLocationField: EditText? = null

    private var serverHandler: ServerHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_createevent)

        eventNameField = findViewById(R.id.event_name)
        eventDescriptionField = findViewById(R.id.event_description)
        eventDateField = findViewById(R.id.event_date)
        eventTimeField = findViewById(R.id.event_time)
        eventLocationField = findViewById(R.id.event_location)

        serverHandler = ServerHandler.serverHandler
    }

    fun publishEvent() {
        val eventName = eventNameField!!.text.toString()
        val eventDescription = eventDescriptionField!!.text.toString()
        val eventDate = eventDateField!!.text.toString()
        val eventTime = eventTimeField!!.text.toString()
        val eventDateAndTime = eventDate + "T" + eventTime + "Z"
        val eventLocation = eventLocationField!!.text.toString()
        serverHandler!!.createNewEvent(eventName, eventDateAndTime, eventDescription, eventLocation,
                { it: PlannedEvent ->
                    TODO()
                },
                { it: VolleyError? ->
                    TODO()
                })
    }
}

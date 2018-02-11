package com.coolkids.todo.getTogether

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
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

        eventNameField = findViewById(R.id.create_task_event_name)
        eventDescriptionField = findViewById(R.id.create_task_title)
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
                { event: PlannedEvent ->
                    val goToMainActivity = Intent(this@CreateEventActivity, MainActivity::class.java)
                    this@CreateEventActivity.startActivity(goToMainActivity)
                },
                { e: VolleyError? ->
                    val cantCreateEventError = findViewById<TextView>(R.id.cant_create_event_error)
                    cantCreateEventError.visibility = View.VISIBLE
                })
    }
}

package com.coolkids.todo.getTogether

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class JoinActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_join)

    }

    fun attemptJoin(v : View){
        val code = findViewById<EditText>(R.id.editText8)
        ServerHandler.serverHandler.joinEvent(code.text.toString(),{event->
            goToMain(v)
        },{err->
            findViewById<TextView>(R.id.errorTextJoinEvent).visibility = TextView.VISIBLE;
        })
    }

    fun goToMain(v : View){
        val goToMain = Intent(this, MainActivity::class.java)
        startActivity(goToMain)
    }

}

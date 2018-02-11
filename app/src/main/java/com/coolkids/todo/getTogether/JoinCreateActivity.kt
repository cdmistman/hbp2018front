package com.coolkids.todo.getTogether

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class JoinCreateActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_joincreate)

    }

    fun goToMain(v : View){
        val goToMain = Intent(this, MainActivity::class.java)
        startActivity(goToMain)
    }

    fun goToJoin(v : View){
        val intent = Intent(this, JoinActivity::class.java)
        startActivity(intent)
    }

    fun goToCreate(v : View){
        val intent = Intent(this, CreateEventActivity::class.java)
        startActivity(intent)
    }
}

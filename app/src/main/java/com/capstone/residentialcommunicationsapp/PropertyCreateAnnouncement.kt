package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PropertyCreateAnnouncement : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_create_announcement)

        val createAnnouncementBtn = findViewById<Button>(R.id.createAnnouncementBtn)
        createAnnouncementBtn.setOnClickListener {
            val intent = Intent(this, PropertySuccessfulCreation::class.java)
            startActivity(intent)
        }
    }
}

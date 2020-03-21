package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PropertyHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_home_screen)

        val propCheckIssuesBtn = findViewById<Button>(R.id.propCheckIssuesBtn)
        propCheckIssuesBtn.setOnClickListener {
            val intent = Intent(this, PropertyViewIssues::class.java)
            startActivity(intent)
        }

        val createAnnouncementBtn = findViewById<Button>(R.id.createAnnouncementBtn)
        createAnnouncementBtn.setOnClickListener {
            val intent = Intent(this, PropertyCreateAnnouncement::class.java)
            startActivity(intent)
        }
    }
}

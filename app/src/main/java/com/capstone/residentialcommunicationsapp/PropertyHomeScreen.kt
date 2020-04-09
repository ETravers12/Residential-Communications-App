package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.residentialcommunicationsapp.datamodels.PropertyManagerViewModel

class PropertyHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_home_screen)

        val model: PropertyManagerViewModel by viewModels()
        model.fetchPropertyManagerUsers();

        /* model.issuesLiveData.observe(this, Observer {
            Log.d(issue.type, "issues")
        })*/

        val id = intent.getIntExtra("propertyManagerId", 0);
        val name = intent.getStringExtra("name")

        val propHomeText = findViewById<TextView>(R.id.prop_home_text)
        propHomeText.text = "Property Manager ID: ${id}\nName: ${name}"


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

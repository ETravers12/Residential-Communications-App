package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.PropertyManager
import com.capstone.residentialcommunicationsapp.datamodels.PropertyManagerViewModel

class PropertyHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_home_screen)

        val model: PropertyManagerViewModel by viewModels()
        model.fetchPropertyManagerUsers();

        val placeholder = findViewById<TextView>(R.id.prop_home_text)

        placeholder.text = "Loading...";
        model.propertyManagerUsersLiveData.observe(this, Observer<List<PropertyManager>>{ users ->
            placeholder.text = "property user " + users[0].propertyName + " retrieved"
        })

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

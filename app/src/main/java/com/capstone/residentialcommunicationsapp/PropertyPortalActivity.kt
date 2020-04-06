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

class PropertyPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_portal)

        val model: PropertyManagerViewModel by viewModels()
        model.fetchPropertyManagerUsers();

        val placeholder = findViewById<TextView>(R.id.prop_home_text)

        placeholder.text = "Loading...";
        model.propertyManagerUsersLiveData.observe(this, Observer<List<PropertyManager>>{users ->
            placeholder.text = "property user " + users[0].propertyManagerName + " retrieved"
        })

        val propLoginSubmit = findViewById<Button>(R.id.propLoginSubmit)
        propLoginSubmit.setOnClickListener {
            val intent = Intent(this, PropertyHomeScreen::class.java)
            startActivity(intent)
        }
    }
}

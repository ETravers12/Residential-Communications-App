package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Notifications
import com.capstone.residentialcommunicationsapp.datamodels.NotificationsViewModel
import com.capstone.residentialcommunicationsapp.datamodels.Property
import com.capstone.residentialcommunicationsapp.datamodels.PropertyViewModel

class PropertyCreateAnnouncement : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_create_announcement)

        var propertyId = findViewById<EditText>(R.id.createAnnouncementPropertyId)
        var message = findViewById<EditText>(R.id.announcementTextInput)

        val pmId = intent.getIntExtra("propertyManagerId", 0)

        val createAnnouncementError = findViewById<TextView>(R.id.createAnnouncementError)

        val NotificationsModel: NotificationsViewModel by viewModels()

        val model: PropertyViewModel by viewModels()
        model.fetchPropertyByPropertyManagerId(pmId);

        fun isValid(propertyId: Int, message: String) : Boolean {
            var validity = true
            var count = 0

            model.propertyLiveData.observe(this, Observer<List<Property>>{ property ->
                if (property != null) {
                    property.forEach {
                            prop ->
                        if (prop.id == propertyId && !message.isNullOrEmpty()) {
                            count += 1
                        }
                        else {
                            validity = false
                        }
                    }
                }
                else {
                    validity = false
                }
            })

            if (count == 1) {
                validity = true
            }

            return validity
        }

        val createAnnouncementBtn = findViewById<Button>(R.id.createAnnouncementBtn)
        createAnnouncementBtn.setOnClickListener {
            if (isValid(propertyId.text.toString().toIntOrNull() ?: -1, message.text.toString())) {
                NotificationsModel.createNotification(propertyId.text.toString().toIntOrNull() ?: -1,
                    message.text.toString())
            }
            else {
                createAnnouncementError.setText("Invalid information or an empty field was entered. Please try again.")
            }
        }

        NotificationsModel.notificationsCreationLiveData.observe(this, Observer<Notifications> { note ->
            if (note != null) {
                val intent = Intent(this, PropertySuccessfulCreation::class.java)
                intent.putExtra("propertyManagerId", pmId)
                startActivity(intent)
            }
            else {
                createAnnouncementError.setText("An error has occurred! Please try again.")
            }
        })

    }
}

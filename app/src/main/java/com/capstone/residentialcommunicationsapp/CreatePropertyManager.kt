package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.PropertyManager
import com.capstone.residentialcommunicationsapp.datamodels.PropertyManagerViewModel

class CreatePropertyManager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_property_manager)

        var name = findViewById<EditText>(R.id.propertyManagerNameText)
        var username = findViewById<EditText>(R.id.propertyManagerUsernameText)
        var password = findViewById<EditText>(R.id.propertyManagerPasswordText)

        val createPropertyManagerError = findViewById<TextView>(R.id.createPropertyManagerError)

        val PropertyManagerModel: PropertyManagerViewModel by viewModels()

        fun isValid(name: String, username: String, password: String) : Boolean {
            var validity = true

            if (name.isNullOrEmpty() || username.isNullOrEmpty() || password.isNullOrEmpty()) {
                validity = false
            }

            return validity
        }

        val createPropertyManagerSubmitBtn = findViewById<Button>(R.id.createPropertyManagerSubmitBtn)
        createPropertyManagerSubmitBtn.setOnClickListener {
            if (isValid(name.text.toString(), username.text.toString(), password.text.toString())) {
                PropertyManagerModel.createPropertyManager(name.text.toString(), username.text.toString(), password.text.toString())
            }
            else {
                createPropertyManagerError.setText("Invalid information or an empty field was entered. Please try again.")
            }
        }

        PropertyManagerModel.propertyManagerCreationLiveData.observe(this, Observer<PropertyManager> { pm ->
            if (pm != null) {
                val intent = Intent(this, PropertyPortalActivity::class.java)
                startActivity(intent)
            }
            else {
                createPropertyManagerError.setText("An error has occurred! Please try again.")
            }
        })
    }
}

package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Property
import com.capstone.residentialcommunicationsapp.datamodels.PropertyViewModel

class CreateProperty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_property)

        var name = findViewById<EditText>(R.id.propertyNameText)
        val pmId = intent.getIntExtra("propertyManagerId", 0)

        val createPropertyError = findViewById<TextView>(R.id.createPropertyError)

        val PropertyModel: PropertyViewModel by viewModels()

        fun isValid(name: String) : Boolean {
            var validity = true

            if (name.isNullOrEmpty()) {
                validity = false
            }

            return validity
        }

        val createPropertySubmitBtn = findViewById<Button>(R.id.createPropertySubmitBtn)
        createPropertySubmitBtn.setOnClickListener {
            if (isValid(name.text.toString())) {
                PropertyModel.createProperty(name.text.toString(), pmId)
            }
            else {
                createPropertyError.setText("Invalid information or an empty field was entered. Please try again.")
            }
        }

        PropertyModel.propertyCreationLiveData.observe(this, Observer<Property> { property ->
            if (property != null) {
                val intent = Intent(this, NewPropertyAddedSuccessfully::class.java)
                intent.putExtra("propertyManagerId", pmId)
                startActivity(intent)
            }
            else {
                createPropertyError.setText("An error has occurred! Please try again.")
            }
        })
    }
}

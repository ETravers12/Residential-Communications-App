package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Property
import com.capstone.residentialcommunicationsapp.datamodels.PropertyViewModel

class NewPropertyAddedSuccessfully : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_property_added_successfully)

        var intent = getIntent()

        val pmId = intent.getIntExtra("propertyManagerId", 0)

        val newPropertyIdNumber = findViewById<TextView>(R.id.newPropertyIdNumber)

        val model: PropertyViewModel by viewModels()
        model.fetchPropertyByPropertyManagerId(pmId);

        // I need help here
        fun findMaxPropertyId(): Int {

            var maxPropertyId = 0

            model.propertyLiveData.observe(this, Observer<List<Property>> { property ->
                if (property != null) {
                    property.forEach { prop ->
                        if (prop.id > maxPropertyId) {
                            maxPropertyId = prop.id
                        }
                    }
                }
            })
            return maxPropertyId
        }

        val maxValue = findMaxPropertyId()
        newPropertyIdNumber.setText(maxValue.toString())

        val propertyCreationReturnBtn = findViewById<Button>(R.id.propertyCreationReturnBtn)
        propertyCreationReturnBtn.setOnClickListener {
            val pmId = intent.getIntExtra("propertyManagerId", 0);
            val intent = Intent(this, PropertyHomeScreen::class.java)
            intent.putExtra("propertyManagerId", pmId);
            startActivity(intent)
        }
    }
}

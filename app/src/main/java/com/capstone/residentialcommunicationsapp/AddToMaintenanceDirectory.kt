package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Maintenance
import com.capstone.residentialcommunicationsapp.datamodels.MaintenanceViewModel

class AddToMaintenanceDirectory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_maintenance_directory)

        var name = findViewById<EditText>(R.id.addMaintenanceName)
        var phoneNumber = findViewById<EditText>(R.id.addMaintenancePhoneNumber)
        val pmId = intent.getIntExtra("propertyManagerId", 0)

        val addMaintenanceError = findViewById<TextView>(R.id.addMaintenanceError)

        val MaintenanceModel: MaintenanceViewModel by viewModels()

        fun isValid(name: String, phoneNumber: String) : Boolean {
            var validity = true

            if (name.isNullOrEmpty() || phoneNumber.isNullOrEmpty()) {
                validity = false
            }

            return validity
        }

        val addMaintenanceContactBtn = findViewById<Button>(R.id.addMaintenanceContactBtn)
        addMaintenanceContactBtn.setOnClickListener {
            if (isValid(name.text.toString(), phoneNumber.text.toString())) {
                MaintenanceModel.createMaintenance(name.text.toString(), phoneNumber.text.toString(), pmId)
            }
            else {
                addMaintenanceError.setText("Invalid information or an empty field was entered. Please try again.")
            }
        }

        MaintenanceModel.maintenanceCreationLiveData.observe(this, Observer<Maintenance> { maintenance ->
            if (maintenance != null) {
                val pmId = intent.getIntExtra("propertyManagerId", 0);
                val intent = Intent(this, PropertyAddMaintenanceSuccess::class.java)
                intent.putExtra("propertyManagerId", pmId)
                startActivity(intent)
            }
            else {
                addMaintenanceError.setText("An error has occurred! Please try again.")
            }
        })

    }
}

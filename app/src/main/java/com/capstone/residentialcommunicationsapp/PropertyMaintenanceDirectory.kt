package com.capstone.residentialcommunicationsapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Maintenance
import com.capstone.residentialcommunicationsapp.datamodels.MaintenanceViewModel

class PropertyMaintenanceDirectory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_maintenance_directory)

        val model: MaintenanceViewModel by viewModels()
        model.fetchMaintenance();

        model.maintenanceLiveData.observe(this, Observer<List<Maintenance>>{ maintenance ->
            if (maintenance != null) {
                val propMaintenanceDirectoryBox = findViewById<TextView>(R.id.propMaintenanceDirectoryBox)
                propMaintenanceDirectoryBox.text = "Maintenance Name: ${maintenance[0].name}\n" +
                        "Phone Number: ${maintenance[0].phoneNumber}"
            }
        })
    }
}

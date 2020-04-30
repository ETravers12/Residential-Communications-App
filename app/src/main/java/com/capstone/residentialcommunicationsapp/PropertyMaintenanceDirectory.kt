package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.residentialcommunicationsapp.datamodels.Maintenance
import com.capstone.residentialcommunicationsapp.datamodels.MaintenanceViewModel

class PropertyMaintenanceDirectory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_maintenance_directory)

        var intent = getIntent()

        val pmId = intent.getIntExtra("propertyManagerId", 0)

        val recycler = findViewById<RecyclerView>(R.id.propertyMaintenanceRecycler)

        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val model: MaintenanceViewModel by viewModels()
        model.fetchMaintenanceByPropertyManagerId(pmId);

        model.maintenanceLiveData.observe(this, Observer<List<Maintenance>>{ maintenance ->
            if (maintenance != null) {
                val adapter = Adapter(this, maintenance)
                recycler.adapter = adapter
            }
        })

        val addMaintenanceBtn = findViewById<Button>(R.id.addMaintenanceBtn)
        addMaintenanceBtn.setOnClickListener {
            val pmId = intent.getIntExtra("propertyManagerId", 0);
            val intent = Intent(this, AddToMaintenanceDirectory::class.java)
            intent.putExtra("propertyManagerId", pmId)
            startActivity(intent)
        }
    }
}

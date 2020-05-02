package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.residentialcommunicationsapp.datamodels.TenantIssue
import com.capstone.residentialcommunicationsapp.datamodels.TenantIssueViewModel

class PropertyHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_home_screen)

        val pmId = intent.getIntExtra("propertyManagerId", 0)

        val recycler = findViewById<RecyclerView>(R.id.propertyHomeRecycler)

        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val model: TenantIssueViewModel by viewModels()
        model.fetchTenantIssuesByPropertyManagerId(pmId);

        model.tenantIssuesLiveData.observe(this, Observer<List<TenantIssue>>{ tenantIssue ->
            if (tenantIssue != null) {
                val adapter = Adapter(this, tenantIssue)
                recycler.adapter = adapter
            }
        })

        val propCheckMaintenanceBtn = findViewById<Button>(R.id.propCheckMaintenanceBtn)
        propCheckMaintenanceBtn.setOnClickListener {
            val pmId = intent.getIntExtra("propertyManagerId", 0);
            val intent = Intent(this, PropertyMaintenanceDirectory::class.java)
            intent.putExtra("propertyManagerId", pmId);
            startActivity(intent)
        }

        val createAnnouncementBtn = findViewById<Button>(R.id.createAnnouncementBtn)
        createAnnouncementBtn.setOnClickListener {
            val pmId = intent.getIntExtra("propertyManagerId", 0);
            val intent = Intent(this, PropertyCreateAnnouncement::class.java)
            intent.putExtra("propertyManagerId", pmId);
            startActivity(intent)
        }
    }
}

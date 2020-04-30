package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.residentialcommunicationsapp.datamodels.Issue
import com.capstone.residentialcommunicationsapp.datamodels.IssueViewModel

class PropertyHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_home_screen)

        val pmId = intent.getIntExtra("propertyManagerId", 0)

        val recycler = findViewById<RecyclerView>(R.id.propertyHomeRecycler)

        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val model: IssueViewModel by viewModels()
        model.fetchIssuesByPropertyManagerId(pmId);

        model.issuesLiveData.observe(this, Observer<List<Issue>>{ issue ->
            if (issue != null) {
                val adapter = Adapter(this, issue)
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

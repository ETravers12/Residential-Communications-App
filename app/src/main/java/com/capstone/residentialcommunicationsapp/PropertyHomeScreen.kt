package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.residentialcommunicationsapp.datamodels.*

class PropertyHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_home_screen)

        val pmId = intent.getIntExtra("propertyManagerId", 0)

        val recycler = findViewById<RecyclerView>(R.id.propertyHomeRecycler)

        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val model: IssueViewModel by viewModels()
        val TenantModel: TenantViewModel by viewModels()

        model.fetchIssuesByPropertyManagerId(pmId);
        TenantModel.fetchTenantUsers();

        model.issuesLiveData.observe(this, Observer<List<Issue>>{ issues ->
            if (issues != null) {
                TenantModel.tenantUsersLiveData.observe(this, Observer<List<Tenant>> { tenants ->
                    val tenantIssueAgg: MutableList<TenantIssue> = mutableListOf();

                    if(tenants != null) {
                        issues.forEach { issue ->
                            tenantIssueAgg.add(
                                TenantIssue(
                                    issue,
                                    tenants.find { tenant ->
                                        tenant.id == issue.tenantId
                            }))
                        }

                        val adapter = Adapter(this, tenantIssueAgg)
                        recycler.adapter = adapter
                    }
                })
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

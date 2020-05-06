package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

        val propertyHomeTextView = findViewById<TextView>(R.id.propHomeIssuesText)

        val recycler = findViewById<RecyclerView>(R.id.propertyHomeRecycler)

        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val model: IssueViewModel by viewModels()
        val TenantModel: TenantViewModel by viewModels()

        model.fetchIssuesByPropertyManagerId(pmId);
        TenantModel.fetchTenantUsers();

        model.issuesLiveData.observe(this, Observer<List<Issue>>{ issues ->
            if (issues != null && issues.size > 0) {
                propertyHomeTextView.setText("Tenant Issues")
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
            else {
                propertyHomeTextView.setText("There are currently no tenant issues.")
            }
        })

        val createNewPropertyBtn = findViewById<Button>(R.id.createNewPropertyBtn)
        createNewPropertyBtn.setOnClickListener {
            val pmId = intent.getIntExtra("propertyManagerId", 0);
            val intent = Intent(this, CreateProperty::class.java)
            intent.putExtra("propertyManagerId", pmId);
            startActivity(intent)
        }

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

        val propertySignOutBtn = findViewById<Button>(R.id.propertySignOutBtn)
        propertySignOutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

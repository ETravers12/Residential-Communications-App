package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Notifications
import com.capstone.residentialcommunicationsapp.datamodels.NotificationsViewModel

class TenantHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_home_screen)

        val announcementText = findViewById<TextView>(R.id.tenantHomeText)
        announcementText.text = "COMMUNITY ANNOUNCEMENTS\n\n"

        val model: NotificationsViewModel by viewModels()
        model.fetchNotifications();

       model.notificationsLiveData.observe(this, Observer<List<Notifications>>{ notification ->
           if (notification != null) {
               val tenantHomeText = findViewById<TextView>(R.id.tenantHomeText)
               tenantHomeText.text = "Community Announcements\n\nProperty ID: ${notification[0].propertyId}\n" +
                       "Message: ${notification[0].message}\n\n"
           }
           else {
               val tenantHomeText = findViewById<TextView>(R.id.tenantHomeText)
               tenantHomeText.text = "No New Announcements"
           }
        })

        val issueCreationBtn = findViewById<Button>(R.id.issueCreationBtn)
        issueCreationBtn.setOnClickListener {
            val intent = Intent(this, TenantCreateIssue::class.java)
            startActivity(intent)
        }

        val tenCheckIssuesBtn = findViewById<Button>(R.id.tenCheckIssuesBtn)
        tenCheckIssuesBtn.setOnClickListener {
            val intent = Intent(this, TenantViewIssues::class.java)
            startActivity(intent)
        }
    }
}

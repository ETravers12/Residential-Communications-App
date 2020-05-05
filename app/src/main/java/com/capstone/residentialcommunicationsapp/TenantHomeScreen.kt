package com.capstone.residentialcommunicationsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.residentialcommunicationsapp.datamodels.Notifications
import com.capstone.residentialcommunicationsapp.datamodels.NotificationsViewModel

class TenantHomeScreen : AppCompatActivity() {

    @SuppressLint("WrongViewCast", "WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_home_screen)

        val tenantHomeAnnouncementText = findViewById<TextView>(R.id.tenantHomeAnnouncementText)

        val tenId = intent.getIntExtra("tenantId", 0)

        val recycler = findViewById<RecyclerView>(R.id.tenantHomeRecycler)

        recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val model: NotificationsViewModel by viewModels()
        model.fetchNotificationsByTenantId(tenId);

        model.notificationsLiveData.observe(this, Observer<List<Notifications>>{ note ->
            if (note != null) {
                tenantHomeAnnouncementText.setText("Community Announcements")
                val adapter = Adapter(this, note)
                recycler.adapter = adapter
            }
            else {
                tenantHomeAnnouncementText.setText("There are currently no announcements.")
            }
        })

        val issueCreationBtn = findViewById<Button>(R.id.issueCreationBtn)
        issueCreationBtn.setOnClickListener {
            val tenId = intent.getIntExtra("tenantId", 0)
            val intent = Intent(this, TenantCreateIssue::class.java)
            intent.putExtra("tenantId", tenId)
            startActivity(intent)
        }

        val tenCheckIssuesBtn = findViewById<Button>(R.id.tenCheckIssuesBtn)
        tenCheckIssuesBtn.setOnClickListener {
            val tenId = intent.getIntExtra("tenantId", 0)
            val intent = Intent(this, TenantViewIssues::class.java)
            intent.putExtra("tenantId", tenId)
            startActivity(intent)
        }
    }
}

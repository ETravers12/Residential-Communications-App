package com.capstone.residentialcommunicationsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Notifications
import com.capstone.residentialcommunicationsapp.datamodels.NotificationsViewModel

class TenantHomeScreen : AppCompatActivity() {

    @SuppressLint("WrongViewCast", "WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_home_screen)

        val recycler = findViewById<RecyclerView>(R.id.tenantHomeRecycler)

        recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        var notes = listOf<Notifications>()

        val model: NotificationsViewModel by viewModels()
        model.fetchNotifications();

        model.notificationsLiveData.observe(this, Observer<List<Notifications>>{ note ->
            if (note != null) {
                val adapter = Adapter(note)
                recycler.adapter = adapter
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

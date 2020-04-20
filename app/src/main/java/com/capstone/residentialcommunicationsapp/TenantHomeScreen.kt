package com.capstone.residentialcommunicationsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.residentialcommunicationsapp.datamodels.Notifications

class TenantHomeScreen : AppCompatActivity() {

    @SuppressLint("WrongViewCast", "WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_home_screen)

        val recycler = findViewById<RecyclerView>(R.id.tenantRecyclerAnnouncement)

        recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val notes = ArrayList<Notifications>()

        // NOT SURE WHAT TO DO HERE TO GET THE LIST OF NOTIFICATIONS TO DISPLAY. Also, is it ok to keep it as an arraylist here instead of a mutableList?

        val adapter = Adapter(notes)

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

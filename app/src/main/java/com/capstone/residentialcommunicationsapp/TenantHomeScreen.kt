package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Issue
import com.capstone.residentialcommunicationsapp.datamodels.IssueViewModel

class TenantHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_home_screen)

        val model: IssueViewModel by viewModels()
        model.fetchIssues();

        model.issuesLiveData.observe(this, Observer<Issue>{ issue ->
            if (issue != null) {
                val id = intent.getIntExtra("id", issue.id);
                val type = intent.getStringExtra("type", issue.type)

                startActivity(intent)
            }

        })

        val id = intent.getIntExtra("tenantId", 0);

        // put passed in intent extras in text view
        val tenantHomeText = findViewById<TextView>(R.id.tenantHomeText)
        tenantHomeText.text = id.toString()

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

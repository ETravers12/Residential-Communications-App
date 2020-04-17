package com.capstone.residentialcommunicationsapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Issue
import com.capstone.residentialcommunicationsapp.datamodels.IssueViewModel

class TenantViewIssues : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_view_issues)

        val model: IssueViewModel by viewModels()
        model.fetchIssues();

        model.issuesLiveData.observe(this, Observer<List<Issue>>{ issue ->
            if (issue != null) {
                val tenantViewIssuesBox = findViewById<TextView>(R.id.tenantViewIssuesBox)
                tenantViewIssuesBox.text = "Issue ID: ${issue[0].id}\nType: ${issue[0].type}\n" +
                        "Description: ${issue[0].description}"
            }
        })
    }

}

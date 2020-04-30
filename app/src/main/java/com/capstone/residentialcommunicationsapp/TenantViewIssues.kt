package com.capstone.residentialcommunicationsapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.residentialcommunicationsapp.datamodels.Issue
import com.capstone.residentialcommunicationsapp.datamodels.IssueViewModel

class TenantViewIssues : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_view_issues)

        val tenId = intent.getIntExtra("tenantId", 0)

        val recycler = findViewById<RecyclerView>(R.id.tenantIssuesRecycler)

        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val model: IssueViewModel by viewModels()
        model.fetchIssuesByTenantId(tenId);

        model.issuesLiveData.observe(this, Observer<List<Issue>>{ issue ->
            if (issue != null) {
                val adapter = Adapter(this, issue)
                recycler.adapter = adapter
            }
        })
    }

}

package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Issue
import com.capstone.residentialcommunicationsapp.datamodels.IssueViewModel


class TenantCreateIssue : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_create_issue)

        val tenId = intent.getIntExtra("tenantId", 0)

        val typeInput = findViewById<EditText>(R.id.issueTypeText)
        val urgencyLevelInput = findViewById<EditText>(R.id.issueUrgencyLevelText)
        val descriptionInput = findViewById<EditText>(R.id.issueDescriptionText)

        val createIssueError = findViewById<TextView>(R.id.createIssueError)

        val IssueModel: IssueViewModel by viewModels()

        fun isValid(type: String, urgencyLevel: Int, description: String) : Boolean {
            var validity = true

            if (type.isNullOrEmpty() || urgencyLevel <= 0 || description.isNullOrEmpty()) {

            }

            return validity
        }

        val createIssueBtn = findViewById<Button>(R.id.createIssueBtn)
        createIssueBtn.setOnClickListener {
            if (isValid(typeInput.text.toString(), urgencyLevelInput.text.toString().toIntOrNull() ?: -1,
                    descriptionInput.text.toString())) {
                IssueModel.createIssue(typeInput.text.toString(), descriptionInput.text.toString(),
                    urgencyLevelInput.text.toString().toIntOrNull() ?: -1, tenId)
            }
            else {
                createIssueError.setText("Invalid information or an empty field was entered. Please try again.")
            }
        }

        IssueModel.issuesCreationLiveData.observe(this, Observer<Issue> { issue ->
            if (issue != null) {
                val intent = Intent(this, TenantSuccessfulCreation::class.java)
                intent.putExtra("tenantId", tenId)
                startActivity(intent)
            }
            else {
                createIssueError.setText("An error has occurred! Please try again.")
            }
        })

    }
}

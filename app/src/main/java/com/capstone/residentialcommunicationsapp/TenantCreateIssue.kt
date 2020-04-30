package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class TenantCreateIssue : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_create_issue)

        val typeInput = findViewById<EditText>(R.id.issueTypeText)
        val urgencyLevelInput = findViewById<EditText>(R.id.issueUrgencyLevelText)
        val descriptionInput = findViewById<EditText>(R.id.issueDescriptionText)

        val createIssueBtn = findViewById<Button>(R.id.createIssueBtn)
        createIssueBtn.setOnClickListener {
            val tenId = intent.getIntExtra("tenantId", 0)
            val intent = Intent(this, TenantSuccessfulCreation::class.java)
            intent.putExtra("tenantId", tenId)

            val type = typeInput.text
            val urgency = urgencyLevelInput.text
            val description = descriptionInput.text

            startActivity(intent)
        }
    }
}

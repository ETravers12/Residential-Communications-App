package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TenantCreateIssue : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_create_issue)

        val createIssueBtn = findViewById<Button>(R.id.createIssueBtn)
        createIssueBtn.setOnClickListener {
            val intent = Intent(this, TenantSuccessfulCreation::class.java)
            startActivity(intent)
        }
    }
}

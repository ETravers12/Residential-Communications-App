package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TenantSuccessfulCreation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_successful_creation)

        val tenantReturnBtn = findViewById<Button>(R.id.tenantReturnBtn)
        tenantReturnBtn.setOnClickListener {
            val intent = Intent(this, TenantHomeScreen::class.java)
            startActivity(intent)
        }
    }
}

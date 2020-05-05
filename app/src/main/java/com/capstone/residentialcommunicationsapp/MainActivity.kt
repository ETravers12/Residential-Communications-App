package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val propertyPortalBtn = findViewById<Button>(R.id.propertyPortalBtn)
        propertyPortalBtn.setOnClickListener {
                val intent = Intent(this, PropertyPortalActivity::class.java)
                startActivity(intent)
        }

        val tenantPortalBtn = findViewById<Button>(R.id.tenantPortalBtn)
                tenantPortalBtn.setOnClickListener {
                    val intent = Intent(this, TenantPortalActivity::class.java)
                    startActivity(intent)
        }

        val createTenantButton = findViewById<Button>(R.id.createTenantButton)
        createTenantButton.setOnClickListener {
            val intent = Intent(this, CreateTenant::class.java)
            startActivity(intent)
        }

        val createPropertyManagerButton = findViewById<Button>(R.id.createPropertyManagerButton)
        createPropertyManagerButton.setOnClickListener {
            val intent = Intent(this, CreatePropertyManager::class.java)
            startActivity(intent)
        }
    }
}

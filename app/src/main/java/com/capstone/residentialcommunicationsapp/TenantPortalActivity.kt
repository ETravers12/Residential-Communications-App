package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TenantPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_portal)

        val tenLoginSubmit = findViewById<Button>(R.id.tenLoginSubmit)
        tenLoginSubmit.setOnClickListener {
            val intent = Intent(this, TenantHomeScreen::class.java)
            startActivity(intent)
        }
    }
}

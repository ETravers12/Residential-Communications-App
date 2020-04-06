package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PropertyPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_portal)

        val propLoginSubmit = findViewById<Button>(R.id.propLoginSubmit)
        propLoginSubmit.setOnClickListener {
            val intent = Intent(this, PropertyHomeScreen::class.java)
            startActivity(intent)
        }
    }
}

package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.PropertyManager
import com.capstone.residentialcommunicationsapp.datamodels.PropertyManagerViewModel

class PropertyPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_portal)

        val model: PropertyManagerViewModel by viewModels()
        model.fetchPropertyManagerUsers();

        val propLoginSubmit = findViewById<Button>(R.id.propLoginSubmit)
        propLoginSubmit.setOnClickListener {
            val intent = Intent(this, PropertyHomeScreen::class.java)

            //Test code to authenticate user
            val userName = findViewById<EditText>(R.id.propertyManagerUsername).text;
            val password = findViewById<EditText>(R.id.propertyManagerPasswordInput).text;

            model.loginPropertyManager(userName.toString(), password.toString());

            //needed - respond if there's an error
            model.propertyManagerLoginLiveData.observe(this, Observer<PropertyManager>{ user ->
                if (user != null) {
                    intent.putExtra("propertyManagerId", user.id);
                    intent.putExtra("name", user.propertyName)

                    startActivity(intent)
                }
                else {
                    val intent = Intent(this, LoginErrorScreen::class.java)
                    startActivity(intent)
                }
            })
        }
    }
}

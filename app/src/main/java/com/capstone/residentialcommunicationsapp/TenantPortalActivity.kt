package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Tenant
import com.capstone.residentialcommunicationsapp.datamodels.TenantViewModel

class TenantPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_portal)

        val model: TenantViewModel by viewModels()
        model.fetchTenantUsers();

        val placeholder = findViewById<TextView>(R.id.jsonPlaceholder)

        // here, we're updating a placeholder
        placeholder.text = "Loading...";
        model.tenantUsersLiveData.observe(this, Observer<List<Tenant>>{ users ->
            placeholder.text = "user " + users[0].tenantName + " retrieved";
        })

        val tenLoginSubmit = findViewById<Button>(R.id.tenLoginSubmit)
        tenLoginSubmit.setOnClickListener {
            val intent = Intent(this, TenantHomeScreen::class.java)
            startActivity(intent)

            //TODO - figure out how to call authentication endpoints from
            // a button click
                //Test code to authenticate user
    /*        val userName = findViewById<Button>(R.id.tenantUsername).text;
            val password = findViewById<Button>(R.id.tenantPasswordInput).text;

            //model.loginTenant(userName, password);

            //needed - respond if there's an error
            model.tenantLoginLiveData.observe(this, Observer<Tenant>{ user ->
                intent.putExtra("text", user.username + "successfully logged in!");

                startActivity(intent)
            })*/
        }
    }

}

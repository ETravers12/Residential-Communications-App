package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        val tenLoginSubmit = findViewById<Button>(R.id.tenLoginSubmit)
        tenLoginSubmit.setOnClickListener {
            val intent = Intent(this, TenantHomeScreen::class.java)

            //Test code to authenticate user
            val userName = findViewById<EditText>(R.id.tenantUsername).text;
            val password = findViewById<EditText>(R.id.tenantPasswordInput).text;

            model.loginTenant(userName.toString(), password.toString());

            //needed - respond if there's an error
            model.tenantLoginLiveData.observe(this, Observer<Tenant>{ user ->
                if (user != null) {
                    intent.putExtra("tenantId", user.id);
                    intent.putExtra("name", user.tenantName)
                    intent.putExtra("buildingNumber", user.buildNum)
                    intent.putExtra("unitNumber", user.unitNum)
                    intent.putExtra("propertyId", user.propId)

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
